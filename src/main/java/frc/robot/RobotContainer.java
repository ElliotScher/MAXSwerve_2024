// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.AutoRoutine;

public class RobotContainer {
    private final DriveBase m_DriveBase = DriveBase.getInstance();

    private final CommandXboxController m_Controller = new CommandXboxController(0);

    private static SendableChooser<AutoRoutine> mAutoChooser = new SendableChooser<AutoRoutine>();

    public RobotContainer() {
        configureBindings();
        SmartDashboard.putData("Autonomous Mode", mAutoChooser);
    }

    private void configureBindings() {
        m_DriveBase.setDefaultCommand(
            m_DriveBase.driveCommand(
                () -> m_Controller.getLeftY(),
                () -> m_Controller.getRightX()
            )
        );
    }

    public Command getAutonomousCommand() {
        return new InstantCommand(
            () -> m_DriveBase.resetOdometry(
                mAutoChooser.getSelected().getInitialPose()
            ),
            m_DriveBase
        ).andThen(
            mAutoChooser.getSelected()
        );
    }
}
