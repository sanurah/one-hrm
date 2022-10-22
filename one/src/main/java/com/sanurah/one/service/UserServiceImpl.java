package com.sanurah.one.service;

import com.sanurah.one.entity.User;
import com.sanurah.one.entity.VerificationToken;
import com.sanurah.one.exception.EntityNotFoundException;
import com.sanurah.one.exception.VerificationTokenExpiredException;
import com.sanurah.one.model.UserModel;
import com.sanurah.one.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public UserModel registerUser(UserModel userModel) {
        User user = map(userModel);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        user = userRepository.save(user);
        return map(user);
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(
                        user -> {
                            if (user != null) {
                                return map(user);
                            }
                            return new UserModel();
                        }
                ).collect(Collectors.toList());
    }

    public UserModel getUserById(Long id) throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(User.class, id.toString());
        }
        return map(user.get());
    }

    public VerificationToken createUserVerificationToken(UserModel userModel, UUID token) {
        User user = map(userModel);
        return verificationTokenService.createVerificationToken(user, token);
    }

    public UserModel verifyUser(UUID token) throws EntityNotFoundException, VerificationTokenExpiredException {

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        User user = verificationToken.getUser();
        user.setVerified(true);
        user = userRepository.save(user);

        verificationTokenService.deleteVerificationToken(verificationToken);
        return map(user);
    }

    private User map(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setEmail(userModel.getEmail());
        user.setRole(userModel.getRole());

        if (userModel.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userModel.getPassword());
            user.setPassword(encodedPassword);
        }

        return user;
    }

    private UserModel map(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole());
        userModel.setVerified(user.isVerified());
        return userModel;
    }
}
