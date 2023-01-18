// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void disabledPeriodic() {
        for (AutoRoutine routine : RobotContainer.getAutoList()) {
            RobotContainer.getField().getObject(
                routine.getTrajectories().toString()
            ).setPoses(
                List.of()
            );
        }
        for (AutoPath path: RobotContainer.getChooser().getSelected().getTrajectories()) {
            RobotContainer.getField().getObject(
                path.toString()
            )
            .setTrajectory(
                path.getTrajectory()
            );
        }
        RobotContainer.getField().setRobotPose(
            RobotContainer.getChooser().getSelected().getInitialPose()
        );

    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {
    }

    @Override
    public void simulationPeriodic() {}
}