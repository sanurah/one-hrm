package com.sanurah.one.service;

import com.sanurah.one.entity.VerificationToken;
import com.sanurah.one.exception.EntityNotFoundException;
import com.sanurah.one.exception.VerificationTokenExpiredException;
import com.sanurah.one.model.UserModel;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserModel registerUser(UserModel user);

    List<UserModel> getUsers();

    UserModel getUserById(Long id) throws EntityNotFoundException;

    VerificationToken createUserVerificationToken(UserModel userModel, UUID token);

    UserModel verifyUser(UUID token) throws EntityNotFoundException, VerificationTokenExpiredException;
}
