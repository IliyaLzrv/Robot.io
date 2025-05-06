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


public class RobotServiceTests {
    @InjectMocks
    private RobotService robotService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        testUser = new User(1, "test_user", "test_user@example.com", "password123", 1);
    }

    @Test
    public void testCreateRobot() {
        // Create a new robot with blastPower = 75
        Robot robot = new Robot(0, testUser, "BlitzBot", 75, 60, BazookaType.NORMAL, "Red", 1, 100);

        // Call the service to create the robot
        Robot createdRobot = robotService.createRobot(testUser, robot);

        // Verify that the created robot has the expected attributes
        assertNotNull(createdRobot);
        assertEquals(1, createdRobot.getId());  // The ID should be 1 (auto-incremented)
        assertEquals(BazookaType.NORMAL, createdRobot.getBazooka());  // Verify bazooka type
        assertEquals(75, createdRobot.getBlastPower());  // Verify blast power is 75, not 0
    }


    @Test
    public void testUpgradeBazooka() {
        // Create a new robot
        Robot robot = new Robot(0, testUser, "BlitzBot", 75, 60, BazookaType.NORMAL, "Red", 1, 100);
        Robot createdRobot = robotService.createRobot(testUser, robot);

        // Upgrade the bazooka
        Robot upgradedRobot = robotService.upgradeBazooka(createdRobot);
        assertEquals(BazookaType.DOUBLE, upgradedRobot.getBazooka());  // Bazooka should now be DOUBLE
    }
}
