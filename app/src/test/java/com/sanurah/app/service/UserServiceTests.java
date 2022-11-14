package com.sanurah.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sanurah.app.constants.Role;
import com.sanurah.app.entity.User;
import com.sanurah.app.model.UserModel;
import com.sanurah.app.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private static final UUID uuid = UUID.randomUUID();

    UserRepository userRepository = mock(UserRepository.class);
    VerificationTokenService verificationTokenService = mock(VerificationTokenService.class);
    UserService userService = new UserServiceImpl(userRepository, new BCryptPasswordEncoder(), new ModelMapper());

    @Before
    public void setUp() {
        when(userRepository.save(any())).thenReturn(getUser());
        when(userRepository.findAll()).thenReturn(List.of(getUser()));
    }

    @Test
    public void createUser() {
        UserModel userModel = getUserModel();
        userModel = userService.createUser(userModel);

        assertThat(userModel, is(notNullValue()));
        assertThat(userModel.getId(), equalTo(1l));
        assertThat(userModel.getUuid(), equalTo(uuid));
        assertThat(userModel.getUsername(), equalTo("username"));
        assertThat(userModel.getPassword(), equalTo("password"));
        assertThat(userModel.getMatchPassword(), is(nullValue()));
        assertThat(userModel.getRole(), equalTo(Role.ADMIN));
        assertThat(userModel.getVerified(), equalTo(true));
        assertThat(userModel.getActive(), equalTo(true));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void getUsers() {
        List<UserModel> userModels = userService.getUsers();
        assertThat(userModels, is(IsCollectionWithSize.hasSize(1)));
        assertThat(userModels.get(0).getId(), equalTo(1l));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserById() {
        List<UserModel> userModels = userService.getUsers();
        assertThat(userModels, is(IsCollectionWithSize.hasSize(1)));
        verify(userRepository, times(1)).findAll();
    }

    private static User getUser() {
        User user = new User();
        user.setId(1l);
        user.setUuid(uuid);
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.ADMIN);
        user.setVerified(true);
        user.setActive(true);

        return user;
    }

    private static UserModel getUserModel() {
        UserModel userModel = new UserModel();
        userModel.setId(1l);
        userModel.setUuid(uuid);
        userModel.setUsername("username");
        userModel.setPassword("password");
        userModel.setMatchPassword("password");
        userModel.setRole(Role.ADMIN);
        userModel.setVerified(true);
        userModel.setActive(true);

        return userModel;
    }
}
