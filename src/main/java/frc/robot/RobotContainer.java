// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Autos.Grid1.Grid1_Node1_Intake_ChargingStation;
import frc.robot.Autos.Grid1.Grid1_Node1_Node2;
import frc.robot.Autos.Grid1.Grid1_Node1_Node2_Node3;
import frc.robot.Autos.Grid2.Grid2_Node1_Intake_ChargingStation;
import frc.robot.Autos.Grid2.Grid2_Node3_Intake_ChargingStation;
import frc.robot.Autos.Grid3.Grid3_Node3_Intake_ChargingStation;
import frc.robot.Autos.Grid3.Grid3_Node3_Node2;
import frc.robot.Autos.Grid3.Grid3_Node3_Node2_Node1;
import frc.robot.Autos.Priority.ChargingStation;
import frc.robot.Autos.Priority.Grid2_Node1_ChargingStation;
import frc.robot.Autos.Priority.Grid2_Node1_Mobility_ChargingStation;
import frc.robot.Autos.Priority.Mobility_ChargingStation;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class RobotContainer {
    private static final AutoRoutine[] m_AutoList = {
        // Priority
        new ChargingStation(),
        new Mobility_ChargingStation(),
        new Grid2_Node1_ChargingStation(),
        new Grid2_Node1_Mobility_ChargingStation(),
        // Non-Priority
        new Grid1_Node1_Intake_ChargingStation(),
        new Grid1_Node1_Node2(),
        new Grid1_Node1_Node2_Node3(),
        new Grid2_Node1_Intake_ChargingStation(),
        new Grid2_Node3_Intake_ChargingStation(),
        new Grid3_Node3_Intake_ChargingStation(),
        new Grid3_Node3_Node2(),
        new Grid3_Node3_Node2_Node1()
    };
    private static Field2d m_Field = new Field2d();
    private final CommandXboxController m_Controller = new CommandXboxController(0);
    private final CommandJoystick m_Joystick = new CommandJoystick(1);
    private final static SendableChooser<AutoRoutine> m_AutoChooser = new SendableChooser<AutoRoutine>();


    public RobotContainer() {
        configureBindings();
        for (AutoRoutine auto : m_AutoList) {
            m_AutoChooser.addOption(auto.getName(), auto);
        }
        m_AutoChooser.setDefaultOption(
            "ChargingStation",
            new ChargingStation()
        );
        putData();
    }
    
    public static Field2d getField() {
        return m_Field;
    }

    public static SendableChooser<AutoRoutine> getChooser() {
        return m_AutoChooser;
    }

    public static AutoRoutine[] getAutoList() {
        return m_AutoList;
    }

    public static void putData() {
        SmartDashboard.putData("Autonomous Mode", m_AutoChooser);
        SmartDashboard.putData("Field", m_Field);
    }

    private void configureBindings() {

        // XboxController Buttons
        m_Controller.a().onTrue(
            CommandUtililty.balanceCommand()
        );

        // Joystick Buttons
        m_Joystick.button(8).whileTrue(
            CommandUtililty.topNode()
        );

        m_Joystick.button(10).whileTrue(
            CommandUtililty.midNode()
        );

        m_Joystick.button(12).whileTrue(
            CommandUtililty.topNode()
        );

        m_Joystick.button(11).whileTrue(
            CommandUtililty.resetTelevator()
        );

        // Elevator and Telescope adjust
        m_Joystick.povUp().whileTrue(
            CommandUtililty.moveY(1)
        );
        m_Joystick.povDown().whileTrue(
            CommandUtililty.moveY(-1)
        );
        m_Joystick.povUp().and(m_Joystick.button(3)).whileTrue(
            CommandUtililty.moveX(1)
        );
        m_Joystick.povDown().and(m_Joystick.button(3)).whileTrue(
            CommandUtililty.moveY(-1)
        );

        // Drive Axis
        m_Controller.axisGreaterThan(1, 0).onTrue(
            CommandUtililty.driveCommand(
                () -> m_Controller.getLeftY(),
                () -> m_Controller.getRightX()
            )
        );

        m_Controller.axisLessThan(1, 0).onTrue(
            CommandUtililty.driveCommand(
                () -> m_Controller.getLeftY(),
                () -> m_Controller.getRightX()
            )
        );

        m_Controller.axisGreaterThan(4, 0).onTrue(
            CommandUtililty.driveCommand(
                () -> m_Controller.getLeftY(),
                () -> m_Controller.getRightX()
            )
        );

        m_Controller.axisLessThan(4, 0).onTrue(
            CommandUtililty.driveCommand(
                () -> m_Controller.getLeftY(),
                () -> m_Controller.getRightX()
            )
        );
    }

    public Command getAutonomousCommand() {
        return CommandUtililty.autoCommand(
            m_AutoChooser.getSelected()
        );
    }
}