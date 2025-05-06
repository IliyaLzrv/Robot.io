package com.example.robot_io.controller;

import com.example.robot_io.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.robot_io.business.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService playerService;

    // Register a new user (explicitly mapped)
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = playerService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginDetails) {
        Optional<User> user = playerService.login(loginDetails.getEmail(), loginDetails.getPassword());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build()); // Return 401 for unauthorized
    }

    // Get all players
    @GetMapping
    public List<User> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // Get player by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = playerService.getPlayerById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update player by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updatePlayer(@PathVariable int id, @RequestBody User updatedPlayer) {
        User player = playerService.updatePlayer(id, updatedPlayer);
        if (player != null) {
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete player by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        if (playerService.deletePlayer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
