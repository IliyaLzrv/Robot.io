package com.example.robot_io.business;

import com.example.robot_io.domain.Role;
import com.example.robot_io.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> players = new ArrayList<>();

    @Autowired
    private LobbyService lobbyService;

    public User registerUser(User user) {
        user.setId(players.size() + 1);  // Auto-increment ID
        players.add(user);


        lobbyService.createLobby(user);

        return user;
    }
    @PostConstruct
    public void initializeAdmin() {
        // Check if an admin user already exists
        boolean adminExists = players.stream()
                .anyMatch(user -> user.getRole() == Role.ADMIN);

        // If not, create and add the admin user
        if (!adminExists) {
            User admin = new User();
            admin.setId(0); // Set a unique ID
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("adminpassword");
            admin.setRole(Role.ADMIN); // Ensure this user has the ADMIN role
            players.add(admin);
        }
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = players.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst();

        if (user.isPresent() && user.get().isSuspended()) {
            throw new IllegalStateException("This account is suspended.");
        }

        user.ifPresent(lobbyService::createLobby);  // Create a lobby if login is successful

        return user;
    }

    public List<User> getAllPlayers() {
        return players;
    }

    public Optional<User> getPlayerById(int id) {
        return players.stream().filter(player -> player.getId() == id).findFirst();
    }

    public User updatePlayer(int id, User updatedPlayer) {
        Optional<User> playerOptional = getPlayerById(id);
        if (playerOptional.isPresent()) {
            User existingPlayer = playerOptional.get();
            existingPlayer.setUsername(updatedPlayer.getUsername());
            existingPlayer.setEmail(updatedPlayer.getEmail());
            existingPlayer.setPassword(updatedPlayer.getPassword());
            return existingPlayer;
        }
        return null;
    }

    public boolean deletePlayer(int id) {
        return players.removeIf(player -> player.getId() == id);
    }

}
