package com.example.robot_io.controller;

import com.example.robot_io.business.LobbyService;
import com.example.robot_io.domain.Lobby;
import com.example.robot_io.domain.Robot;
import com.example.robot_io.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.robot_io.business.RobotService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/robots")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping
    public ResponseEntity<Robot> createRobot(@RequestBody Robot robot) {
        Robot createdRobot = robotService.createRobot(robot.getUser(), robot);
        return ResponseEntity.ok(createdRobot);
    }

    // Endpoint to upgrade the robot's bazooka
    @PutMapping("/{id}/upgradeBazooka")
    public ResponseEntity<Robot> upgradeBazooka(@PathVariable int id) {

        Optional<Robot> robotOptional = robotService.getRobotById(id);
        if (robotOptional.isPresent()) {
            Robot robot = robotOptional.get();
            robotService.upgradeBazooka(robot);
            return ResponseEntity.ok(robot);
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    private LobbyService lobbyService;

    // Create and select a robot for a user
    @PostMapping("/create")
    public ResponseEntity<Robot> createRobotForUser(@RequestBody Robot robot, @RequestParam int userId, @RequestParam int lobbyId) {

        User user = new User(userId, "exampleUser", "user@example.com", "password", 1);  // Example user object

        // Create a new robot for the user
        Robot createdRobot = robotService.createRobot(user, robot);

        // Update the lobby with the robot
        Lobby updatedLobby = lobbyService.updateLobbyWithRobot(lobbyId, createdRobot);

        if (updatedLobby != null) {
            return ResponseEntity.ok(createdRobot);
        }
        return ResponseEntity.status(400).build();  // If something goes wrong
    }

    @GetMapping("/{id}")
    public ResponseEntity<Robot> getRobotById(@PathVariable int id) {
        Optional<Robot> robot = robotService.getRobotById(id);
        return robot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
