// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.AutoRoutine;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;
    private Trajectory m_Trajectory;
    private Alliance m_LastAlliance;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        try {
            SmartDashboard.getData("Autonomous Mode");
            SmartDashboard.getData("Field");
        } catch (IllegalArgumentException exception) {
            RobotContainer.putData();
        }
    }

    @Override
    public void disabledInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void disabledPeriodic() {
        if (DriverStation.getAlliance() != (m_LastAlliance)) {
            RobotContainer.resetList();
        }
        m_LastAlliance = DriverStation.getAlliance();
        SendableChooser<AutoRoutine> chooser = RobotContainer.getChooser();
        m_Trajectory = chooser.getSelected().getTrajectories()[0].getTrajectory();
        for (int i = 1; i < chooser.getSelected().getTrajectories().length; i++) {
            m_Trajectory = m_Trajectory.concatenate(RobotContainer.getChooser().getSelected().getTrajectories()[i].getTrajectory());
        }
        RobotContainer.getField().getObject(
            "traj"
        )
        .setTrajectory(
            m_Trajectory
        );
        try {
            SmartDashboard.getData("Autonomous Mode");
            SmartDashboard.getData("Field");
        } catch (IllegalArgumentException exception) {
            RobotContainer.putData();
        }
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