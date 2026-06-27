package com.example.job.service;

import com.example.job.dto.response.UserResponse;
import com.example.job.modal.User;
import com.example.job.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws Exception;

    User getUserById(String userId) throws Exception;

    List<User> getAllUsers();

    UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception;

    UserResponse suspendUser(String userId) throws Exception;
    UserResponse deleteUser(String userId) throws Exception;
    UserResponse activateUser(String userId) throws Exception;
}
