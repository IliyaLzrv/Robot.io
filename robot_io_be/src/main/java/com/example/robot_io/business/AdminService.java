package com.example.robot_io.business;

import com.example.robot_io.business.exceptions.UserNotFoundException;
import com.example.robot_io.domain.Role;
import com.example.robot_io.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    private UserService userService;

    public User updateUser(int userId, User updatedUserDetails) {
        return userService.updatePlayer(userId, updatedUserDetails);
    }

    public void suspendUser(int userId) {
        Optional<User> userOptional = userService.getPlayerById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSuspended(true); // Assuming you've added a 'suspended' field to the User class
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }


    public void reactivateUser(User currentUser,int userId) {
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only admins can perform this action.");
        }

        User user = userService.getPlayerById(userId).orElseThrow();
        if (user.isSuspended()) {
            user.setSuspended(false); // Reactivate user
            userService.updatePlayer(userId, user);
        } else {
            throw new IllegalStateException("User is not suspended.");
        }
    }

    public void sendAnnouncement(String message) {

    }
    public void changeBattleField(String newBackground){}
}
