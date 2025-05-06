package com.example.robot_io.business;

import com.example.robot_io.domain.BazookaType;
import com.example.robot_io.domain.Robot;
import com.example.robot_io.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RobotService {
    private List<Robot> robots = new ArrayList<>();

    // Create a new robot
    public Robot createRobot(User user, Robot robot) {
        robot.setId(robots.size() + 1);
        robot.setUser(user);
        if (robot.getBazooka() == null) {
            robot.setBazooka(BazookaType.NORMAL);
        }
        robot.setLevel(1);
        robot.setHp(100);

        robots.add(robot);
        return robot;
    }

    // Get robot by id
    public Optional<Robot> getRobotById(int id) {
        return robots.stream().filter(robot -> robot.getId() == id).findFirst();
    }

    // Get robot by user
    public List<Robot> getRobotsByUser(User user) {
        List<Robot> userRobots = new ArrayList<>();
        for (Robot robot : robots) {
            if (robot.getUser().getId() == user.getId()) {
                userRobots.add(robot);
            }
        }
        return userRobots;
    }
    // Upgrade bazooka
    public Robot upgradeBazooka(Robot robot) {
        if (robot.getBazooka() == BazookaType.NORMAL) {
            robot.setBazooka(BazookaType.DOUBLE);  // Upgrade to double bazooka
        } else if (robot.getBazooka() == BazookaType.DOUBLE) {
            robot.setBazooka(BazookaType.BIGGER);  // Further upgrade
        }
        return robot;
    }

}
