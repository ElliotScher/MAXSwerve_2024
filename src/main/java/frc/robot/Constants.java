// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;
import frc.robot.utilities.AutoPath;

public final class Constants {
    // Actuator Constants
    public static final int k_LeftDriveLeaderID = 1;
    public static final int k_LeftDriveFollowerID = 3;
    public static final int k_RightDriveLeaderID = 2;
    public static final int k_RightDriveFollowerID = 4;

    public static final int k_ElevatorLeaderID = 0;
    public static final int k_ElevatorFollowerID = 0;

    public static final int k_TelescopeID = 0;
    
    // Trajectory Constants
    public static final double k_WheelDiameter = Units.inchesToMeters(6);
    public static final double k_GearBoxRatio = 7.31;
    public static final double k_PositionConversionFactor = (
        (1.0 / k_GearBoxRatio) * (Math.PI * k_WheelDiameter)
    );
    public static final double k_VelocityConversionFactor = (
        (1.0 / k_GearBoxRatio) * (Math.PI * k_WheelDiameter) * (1.0 / 60)
    );
    public static final double k_MaxSpeedMetersPerSecond = 1;
    public static final double k_MaxAccelerationMetersPerSecondSquared = 2;

    public static final double k_RamseteB = 2;
    public static final double k_RamseteZeta = 0.7;

    public static final double k_sVolts = 0.08134;
    public static final double k_vVoltSecondsPerMeter = 2.0181;
    public static final double k_aVoltSecondsSquaredPerMeter = 0.36127;

    public static final double k_pDriveVel = 0.0060339;

    public static final double k_TrackwidthMeters = Units.inchesToMeters(22);
    public static final DifferentialDriveKinematics k_DriveKinematics = new DifferentialDriveKinematics(k_TrackwidthMeters);

    public static final DifferentialDriveVoltageConstraint k_AutoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(
            Constants.k_sVolts,
            Constants.k_vVoltSecondsPerMeter,
            Constants.k_aVoltSecondsSquaredPerMeter),
        Constants.k_DriveKinematics,
        10
    );

    public static final TrajectoryConfig config = new TrajectoryConfig(
            Constants.k_MaxSpeedMetersPerSecond,
            Constants.k_MaxAccelerationMetersPerSecondSquared
        )
        .setKinematics(Constants.k_DriveKinematics)
        .addConstraint(Constants.k_AutoVoltageConstraint);

    // Pitch Constants
    public static final double k_pPitch = 0.005; // Make robot faster
    public static final double k_iPitch = 0; // maybe add this
    public static final double k_dPitch = 0; // see how this works

    // Vision Constants
    public static final double k_CameraHeightMeters = Units.inchesToMeters(21.75);
    public static final double k_GridAprilTagHeightMeters = 0.38;
    public static final double k_CameraPitchRadians = 0;
    public static final double k_DistanceFromGridTagToChargingStation = Units.inchesToMeters(112.81);

    // Elevator Constants
    public static final double k_ElevatorLowNodeSetpoint = 0;
    public static final double k_ElevatorMidNodeSetpoint = 0;
    public static final double k_ElevatorTopNodeSetpoint = 0;

    // Telescope Constants
    public static final double k_TelescopeLowNodeSetpoint = 0;
    public static final double k_TelescopeMidNodeSetpoint = 0;
    public static final double k_TelescopeTopNodeSetpoint = 0;

    // Pathweaver JSON Paths
    public static final AutoPath[] k_Grid1_Node1_Intake_ChargingStation_Blue = {
        new AutoPath("paths/1_Grid1_Node1_Intake_ChargingStation_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Intake_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid1_Node1_Intake_ChargingStation_Red = { // THIS TRAJECTORY HAS NOT YET BEEN CREATED
        new AutoPath("paths/1_Grid1_Node1_Intake_ChargingStation_Red.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Intake_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid1_Node1_Node2_Node3_Blue = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Node3_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Node3_Blue.wpilib.json"),
        new AutoPath("paths/3_Grid1_Node1_Node2_Node3_Blue.wpilib.json"),
        new AutoPath("paths/4_Grid1_Node1_Node2_Node3_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid1_Node1_Node2_Node3_Red = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Node3_Red.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Node3_Red.wpilib.json"),
        new AutoPath("paths/3_Grid1_Node1_Node2_Node3_Red.wpilib.json"),
        new AutoPath("paths/4_Grid1_Node1_Node2_Node3_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid1_Node1_Node2_Blue = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid1_Node1_Node2_Red = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Red.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_Intake_ChargingStation_Blue = {
        new AutoPath("paths/1_Grid2_Node1_Intake_ChargingStation_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid2_Node1_Intake_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_Intake_ChargingStation_Red = {
        new AutoPath("paths/1_Grid2_Node1_Intake_ChargingStation_Red.wpilib.json"),
        new AutoPath("paths/2_Grid2_Node1_Intake_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node3_Intake_ChargingStation_Blue = {
        new AutoPath("paths/1_Grid2_Node1_Intake_ChargingStation_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid2_Node1_Intake_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node3_Intake_ChargingStation_Red = {
        new AutoPath("paths/1_Grid2_Node3_Intake_ChargingStation_Red.wpilib.json"),
        new AutoPath("paths/2_Grid2_Node3_Intake_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Intake_ChargingStation_Blue = {
        new AutoPath("paths/1_Grid3_Node3_Intake_ChargingStation_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Intake_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Intake_ChargingStation_Red = {
        new AutoPath("paths/1_Grid3_Node3_Intake_ChargingStation_Red.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Intake_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Node2_Node1_Blue = {
        new AutoPath("paths/1_Grid3_Node3_Node2_Node1_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Node2_Node1_Blue.wpilib.json"),
        new AutoPath("paths/3_Grid3_Node3_Node2_Node1_Blue.wpilib.json"),
        new AutoPath("paths/4_Grid3_Node3_Node2_Node1_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Node2_Node1_Red = {
        new AutoPath("paths/1_Grid3_Node3_Node2_Node1_Red.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Node2_Node1_Red.wpilib.json"),
        new AutoPath("paths/3_Grid3_Node3_Node2_Node1_Red.wpilib.json"),
        new AutoPath("paths/4_Grid3_Node3_Node2_Node1_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Node2_Blue = {
        new AutoPath("paths/1_Grid3_Node3_Node2_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Node2_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid3_Node3_Node2_Red = {
        new AutoPath("paths/1_Grid3_Node3_Node2_Red.wpilib.json"),
        new AutoPath("paths/2_Grid3_Node3_Node2_Red.wpilib.json")
    };
    public static final AutoPath[] k_ChargingStation_Blue = {
        new AutoPath("paths/ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_ChargingStation_Red = {
        new AutoPath("paths/ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Mobility_ChargingStation_Blue = {
        new AutoPath("paths/Mobility_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Mobility_ChargingStation_Red = {
        new AutoPath("paths/Mobility_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_ChargingStation_Blue = {
        new AutoPath("paths/Grid2_Node1_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_ChargingStation_Red = {
        new AutoPath("paths/Grid2_Node1_ChargingStation_Red.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_Mobility_ChargingStation_Blue = {
        new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation_Blue.wpilib.json")
    };
    public static final AutoPath[] k_Grid2_Node1_Mobility_ChargingStation_Red = {
        new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation_Red.wpilib.json")
    };

}