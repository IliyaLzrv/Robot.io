package com.example.robot_io.controller;

import com.example.robot_io.business.LobbyService;
import com.example.robot_io.domain.Lobby;
import com.example.robot_io.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lobbies")
public class LobbyController {

    @Autowired
    private LobbyService lobbyService;



    // Create a lobby for a user
    @PostMapping("/create")
    public ResponseEntity<Lobby> createLobby(@RequestBody User user) {
        Lobby lobby = lobbyService.createLobby(user);
        return ResponseEntity.ok(lobby);
    }

    // Get all lobbies
    @GetMapping
    public List<Lobby> getAllLobbies() {
        return lobbyService.getAllLobbies();
    }

    // Get lobby by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lobby> getLobbyById(@PathVariable int id) {
        Optional<Lobby> lobby = lobbyService.getLobbyById(id);
        return lobby.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get lobby by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<Lobby> getLobbyByUser(@PathVariable int userId) {
        Optional<Lobby> lobby = lobbyService.getAllLobbies().stream()
                .filter(l -> l.getUser().getId() == userId)
                .findFirst();
        return lobby.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update robot selection status in a lobby
    @PutMapping("/{id}/robot")
    public ResponseEntity<Lobby> updateRobotSelection(@PathVariable int id, @RequestParam boolean robotSelected) {
        Lobby updatedLobby = lobbyService.updateRobotSelection(id, robotSelected);
        if (updatedLobby != null) {
            return ResponseEntity.ok(updatedLobby);
        }
        return ResponseEntity.notFound().build();
    }
}
