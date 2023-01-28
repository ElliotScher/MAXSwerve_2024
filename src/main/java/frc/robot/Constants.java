// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class Constants {
    // Actuator Constants
    public static final int k_LeftDriveLeaderID = 1;
    public static final int k_LeftDriveFollowerID = 3;
    public static final int k_RightDriveLeaderID = 2;
    public static final int k_RightDriveFollowerID = 4;

    public static final int k_ElevatorLeaderID = 6;
    public static final int k_ElevatorFollowerID = 7;

    public static final int k_TelescopeID = 5;
    
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

    public static final double k_sVolts = 0.1877;
    public static final double k_vVoltSecondsPerMeter = 1.7616;
    public static final double k_aVoltSecondsSquaredPerMeter = 0.6554;

    public static final double k_pDriveVel = 0.0036916;

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
    public static final double k_pPitch = SmartDashboard.getNumber("k_PPitch", 0.005); // Make robot faster
    public static final double k_iPitch = SmartDashboard.getNumber("k_IPitch", 0); // maybe add this
    public static final double k_dPitch = SmartDashboard.getNumber("k_DPitch", 0); // see how this works

    // DriveToDistance Constants
    public static final double k_PDistance = 0;
    public static final double k_IDistance = 0;
    public static final double k_DDistance = 0;

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
}