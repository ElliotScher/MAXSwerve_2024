// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Autos.ChargingStation;
import frc.robot.Autos.Grid2_Node1_ChargingStation;
import frc.robot.Autos.Grid2_Node1_Mobility_ChargingStation;
import frc.robot.Autos.Mobility_ChargingStation;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.AutoRoutine;

public class RobotContainer {
    private final DriveBase m_DriveBase = DriveBase.getInstance();

    private final CommandXboxController m_Controller = new CommandXboxController(0);

    private final static SendableChooser<AutoRoutine> m_AutoChooser = new SendableChooser<AutoRoutine>();
    
    private final static Field2d m_Field = new Field2d();

    public RobotContainer() {
        configureBindings();
        m_AutoChooser.addOption("ChargingStation", new ChargingStation());
        m_AutoChooser.addOption("Mobility_ChargingStation", new Mobility_ChargingStation());
        m_AutoChooser.addOption("Grid2_Node1_ChargingStation", new Grid2_Node1_ChargingStation());
        m_AutoChooser.addOption("Grid2_Node1_Mobility_ChargingStation", new Grid2_Node1_Mobility_ChargingStation());

        SmartDashboard.putData("Autonomous Mode", m_AutoChooser);

        SmartDashboard.putData(m_Field);
    }

    private void configureBindings() {
        m_Controller.a().onTrue(
            m_DriveBase.balanceCommand()
        );

        m_DriveBase.setDefaultCommand(
            m_DriveBase.driveCommand(
                () -> m_Controller.getRightX(),
                () -> m_Controller.getLeftY()
            )
        );
    }

    public Command getAutonomousCommand() {
        return new InstantCommand(
            () -> m_DriveBase.resetOdometry(
                m_AutoChooser.getSelected().getInitialPose()
            ),
            m_DriveBase
        ).andThen(
            m_AutoChooser.getSelected()
        );
    }

    public static Field2d getField() {
        return m_Field;
    }

    public static SendableChooser<AutoRoutine> getChooser() {
        return m_AutoChooser;
    }
}