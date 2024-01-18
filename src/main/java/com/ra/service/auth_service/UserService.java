package com.ra.service.auth_service;

import com.ra.model.dto.requset.UserLogin;
import com.ra.model.dto.response.UserResponse;
import com.ra.model.entity.User;

public interface UserService {
    User register(User user);
    UserResponse login(UserLogin userLogin);
}
