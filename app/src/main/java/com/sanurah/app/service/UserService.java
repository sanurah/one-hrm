package com.sanurah.app.service;

import com.sanurah.app.entity.VerificationToken;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserModel createUser(UserModel user);

    List<UserModel> getUsers();

    UserModel getUserById(Long id) throws OneBusinessException;

    VerificationToken createUserVerificationToken(UserModel userModel, UUID token);

    UserModel verifyUser(UUID token) throws OneBusinessException;

    UserModel updatePassword(UserModel userModel) throws OneBusinessException;

    UserModel resendVerificationToken(UUID token) throws OneBusinessException;
}
