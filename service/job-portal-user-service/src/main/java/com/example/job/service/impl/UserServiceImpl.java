package com.example.job.service.impl;

import com.example.job.domain.UserStatus;
import com.example.job.dto.response.UserResponse;
import com.example.job.mapper.UserMapper;
import com.example.job.modal.User;
import com.example.job.payload.UpdateUserRequest;
import com.example.job.repository.UserRepository;
import com.example.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found" + email);
        }

        return user;
    }

    @Override
    public User getUserById(String userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new Exception("User Not found")
                );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception {
        User user = userRepository.findByEmail(email);

        if (req.getFullName() != null) {
            user.setFullName(req.getFullName());
        }
        if (req.getPhone() != null) {
            user.setPhone(req.getPhone());
        }
        if (req.getProfileImage() != null) {
            user.setImageUrl(req.getProfileImage());
        }
        if (req.getFullName() != null) {
            user.setFullName(req.getFullName());
        }

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse suspendUser(String userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspended(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(String userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.DELETED);
        user.setDeleted(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse activateUser(String userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspended(null);
        return UserMapper.toDTO(userRepository.save(user));
    }
}
