package com.sanurah.app.service;

import com.google.common.base.Strings;
import com.sanurah.app.entity.User;
import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.EntityNotFoundException;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import com.sanurah.app.repository.UserRepository;
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

    public UserModel verifyUser(UUID token) throws OneBusinessException {

        VerificationToken verificationToken = verificationTokenService.deleteVerificationToken(token);
        User user = verificationToken.getUser();
        user.setVerified(true);
        user = userRepository.save(user);

        return map(user);
    }

    public UserModel updatePassword(UserModel userModel) throws OneBusinessException {
        verifyPasswordMatch(userModel);
        Optional<User> userResult = userRepository.findById(userModel.getId());
        if (userResult.isEmpty()) {
            throw new EntityNotFoundException(User.class, String.valueOf(userModel.getId()));
        }
        User user = userResult.get();
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user = userRepository.save(user);
        return map(user);
    }

    public UserModel resendVerificationToken(UUID oldToken) throws OneBusinessException {
        VerificationToken verificationToken = verificationTokenService.deleteVerificationToken(oldToken);
        User user = verificationToken.getUser();
        return map(user);
    }

    private void verifyPasswordMatch(UserModel userModel) throws OneBusinessException {
        String password = userModel.getPassword();
        String matchPassword = userModel.getMatchPassword();
        if (!Strings.isNullOrEmpty(password) && !Strings.isNullOrEmpty(matchPassword)
                && password.equals(matchPassword)) {
            return;
        }
        throw new OneBusinessException("Please check that passwords match and are not empty");
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
