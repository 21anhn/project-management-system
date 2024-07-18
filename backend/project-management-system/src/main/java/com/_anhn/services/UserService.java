package com._anhn.services;

import com._anhn.models.User;

public interface UserService {

    User findUserProfileByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findByUserId(Long UserId) throws Exception;

    User updateUsersProjectSize(User user, int number) throws Exception;
}
