package com.sanurah.app.service;

import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel createUser(UserModel user);

    List<UserModel> getUsers();

    UserModel getUserById(Long id) throws OneBusinessException;

    UserModel verifyUser(UserModel verificationToken) throws OneBusinessException;

    UserModel updatePassword(UserModel userModel) throws OneBusinessException;
}
