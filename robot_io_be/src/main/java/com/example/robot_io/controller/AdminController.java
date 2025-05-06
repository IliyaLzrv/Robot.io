package com.example.robot_io.controller;

import com.example.robot_io.business.AdminService;
import com.example.robot_io.business.exceptions.UserNotFoundException;
import com.example.robot_io.domain.Role;
import com.example.robot_io.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.AccessDeniedException;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/player/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User updateUser) {
        return ResponseEntity.ok(adminService.updateUser(userId, updateUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{userId}/suspend")
    public ResponseEntity<String> suspendUser(@PathVariable int userId, @AuthenticationPrincipal User currentUser) {
        // Check if the current user has the ADMIN role
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can perform this action.");
        }

        try {
            // Call the service to suspend the user
            adminService.suspendUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while suspending the user.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{userId}/reactivate")
    public ResponseEntity<Void> reactivateUser(@PathVariable int userId, @AuthenticationPrincipal User currentUser) {
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only admins can perform this action.");
        }
        adminService.reactivateUser(currentUser,userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/announcement")
    public ResponseEntity<Void> sendAnnouncement(@RequestBody String message) {
        adminService.sendAnnouncement(message);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/game/background")
    public ResponseEntity<Void> changeBattlefieldBackground(@RequestBody String newBackground) {
        adminService.changeBattleField(newBackground);
        return ResponseEntity.ok().build();
    }
}
