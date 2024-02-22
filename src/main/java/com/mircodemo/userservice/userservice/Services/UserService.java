package com.mircodemo.userservice.userservice.Services;

import java.util.List;

import com.mircodemo.userservice.userservice.Entities.User;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
}
