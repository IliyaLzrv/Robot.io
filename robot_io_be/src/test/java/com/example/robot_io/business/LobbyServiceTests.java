package com.example.robot_io.business;

import com.example.robot_io.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import java.util.Optional;


public class LobbyServiceTests {
    @InjectMocks
    private LobbyService lobbyService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        testUser = new User(1, "test_user", "test_user@example.com", "password123", 1);
    }

    @Test
    public void testCreateLobby() {
        Lobby lobby = lobbyService.createLobby(testUser);

        assertNotNull(lobby);
        assertEquals(1, lobby.getId());
        assertEquals(testUser, lobby.getUser());
    }

    @Test
    public void testUpdateLobbyWithRobot() {
        Lobby lobby = lobbyService.createLobby(testUser);

        Robot robot = new Robot(1, testUser, "BlitzBot", 75, 60, BazookaType.NORMAL, "Red", 1, 100);
        Lobby updatedLobby = lobbyService.updateLobbyWithRobot(lobby.getId(), robot);

        assertNotNull(updatedLobby);
        assertTrue(updatedLobby.isRobotSelected());  // Robot should be marked as selected
    }
}
