package com.example.robot_io.business;

import com.example.robot_io.domain.Lobby;
import com.example.robot_io.domain.Robot;
import com.example.robot_io.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LobbyService {

    private final List<Lobby> lobbies = new ArrayList<>();

    // Create a new lobby for a user
    public Lobby createLobby(User user) {
        Lobby lobby = new Lobby(lobbies.size() + 1, user);  // Auto-increment lobby ID
        lobbies.add(lobby);
        return lobby;
    }

    // Get all lobbies
    public List<Lobby> getAllLobbies() {
        return lobbies;
    }

    // Get lobby by ID
    public Optional<Lobby> getLobbyById(int id) {
        return lobbies.stream().filter(lobby -> lobby.getId() == id).findFirst();
    }

    // Get lobby by user
    public Optional<Lobby> getLobbyByUser(User user) {
        return lobbies.stream().filter(lobby -> lobby.getUser().getId() == user.getId()).findFirst();
    }

    // Update the lobby when a robot is selected
    public Lobby updateLobbyWithRobot(int lobbyId, Robot robot) {
        Optional<Lobby> lobbyOptional = getLobbyById(lobbyId);
        if (lobbyOptional.isPresent()) {
            Lobby lobby = lobbyOptional.get();
            lobby.setRobotSelected(true);
            return lobby;
        }
        return null;
    }

    // Update lobby robot selection status
    public Lobby updateRobotSelection(int id, boolean robotSelected) {
        Optional<Lobby> lobbyOptional = getLobbyById(id);
        if (lobbyOptional.isPresent()) {
            Lobby lobby = lobbyOptional.get();
            lobby.setRobotSelected(robotSelected);
            return lobby;
        }
        return null;
    }
}

