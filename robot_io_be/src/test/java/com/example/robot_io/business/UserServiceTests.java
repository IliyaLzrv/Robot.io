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

public class UserServiceTests {

    // Mock the LobbyService
    @Mock
    private LobbyService lobbyService;

    // Inject the mocks into the UserService
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into UserService
        MockitoAnnotations.openMocks(this);

        // Set up mock behavior for LobbyService if needed (for example, a default lobby response)
        when(lobbyService.createLobby(any(User.class))).thenReturn(new Lobby(1, new User()));

        // You can also register a test user for testing
        User user1 = new User(1, "test_user", "test_user@example.com", "password123", 1);
        userService.registerUser(user1);
    }

    @Test
    public void testRegisterUser() {
        // Test user registration
        User user = new User(0, "new_user", "new_user@example.com", "password", 1);
        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("new_user", registeredUser.getUsername());
        assertEquals(2, registeredUser.getId());  // ID should be auto-incremented

        // Verify if the lobbyService was called when registering the user
        verify(lobbyService, times(1)).createLobby(user);
    }

    @Test
    public void testLoginUser_Success() {
        // Test successful login
        Optional<User> loggedInUser = userService.login("test_user@example.com", "password123");
        assertTrue(loggedInUser.isPresent());
        assertEquals("test_user", loggedInUser.get().getUsername());
    }

    @Test
    public void testLoginUser_Failure() {
        // Test login failure
        Optional<User> loggedInUser = userService.login("wrong_email@example.com", "password123");
        assertFalse(loggedInUser.isPresent());
    }
}


