package com.example.job.controller;

import com.example.job.dto.response.UserResponse;
import com.example.job.mapper.UserMapper;
import com.example.job.modal.User;
import com.example.job.payload.UpdateUserRequest;
import com.example.job.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * URL: GET /api/users/me
     * Description: Get the profile of the currently logged-in user.
     * Access: Authenticated User
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(Authentication authentication) throws Exception {

        User user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    /**
     * URL: PUT /api/users/me
     * Description: Update the profile of the currently logged-in user.
     * Access: Authenticated User
     *
     * Sample Request Body:
     * {
     *   "fullName": "Manish Kumar",
     *   "phone": "9876543210",
     *   "profileImage": "https://example.com/profile.jpg"
     * }
     */
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequest request) throws Exception {

        return ResponseEntity.ok(
                userService.updateProfile(authentication.getName(), request)
        );
    }

    /**
     * URL: GET /api/users
     * Description: Get all registered users.
     * Access: Admin Only
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * URL: GET /api/users/{userId}
     * Description: Get a specific user by ID.
     * Access: Admin Only
     *
     * Example:
     * GET /api/users/685df8cfd1f2b04b1c52b401
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable String userId) throws Exception {

        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    /**
     * URL: PATCH /api/users/{userId}/suspend
     * Description: Suspend a user account.
     * Access: Admin Only
     *
     * Example:
     * PATCH /api/users/685df8cfd1f2b04b1c52b401/suspend
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(
            @PathVariable String userId) throws Exception {

        return ResponseEntity.ok(userService.suspendUser(userId));
    }

    /**
     * URL: PATCH /api/users/{userId}/activate
     * Description: Activate a suspended user account.
     * Access: Admin Only
     *
     * Example:
     * PATCH /api/users/685df8cfd1f2b04b1c52b401/activate
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<UserResponse> activateUser(
            @PathVariable String userId) throws Exception {

        return ResponseEntity.ok(userService.activateUser(userId));
    }

    /**
     * URL: DELETE /api/users/{userId}
     * Description: Soft delete a user account.
     * Access: Admin Only
     *
     * Example:
     * DELETE /api/users/685df8cfd1f2b04b1c52b401
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable String userId) throws Exception {

        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}