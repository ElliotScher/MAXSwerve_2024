// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final double WHEEL_DIAMETER = Units.inchesToMeters(6);
    public static final double GEAR_BOX_RATIO = 7.31;
    public static final double POSITION_CONVERSION_FACTOR = ((1.0/GEAR_BOX_RATIO) * (Math.PI*WHEEL_DIAMETER));
    public static final double VELOCITY_CONVERSION_FACTOR = ((1.0/GEAR_BOX_RATIO) * (Math.PI*WHEEL_DIAMETER)*(1.0/60));

    public static final double k_MaxSpeedMetersPerSecond = 1;
    public static final double k_MaxAccelerationMetersPerSecondSquared = 2;

    public static final double k_RamseteB = 2;
    public static final double k_RamseteZeta = 0.7;

    public static final double k_sVolts = 0.08134;
    public static final double k_vVoltSecondsPerMeter = 2.0181;
    public static final double k_aVoltSecondsSquaredPerMeter = 0.36127;

    public static final double k_pDriveVel = 0.0060339;
    public static final double k_pPitch = 0;
    public static final double k_iPitch = 0;
    public static final double k_dPitch = 0;

    public static final double k_TrackwidthMeters = Units.inchesToMeters(22);
    public static final DifferentialDriveKinematics k_DriveKinematics = new DifferentialDriveKinematics(k_TrackwidthMeters);

    public static final DifferentialDriveVoltageConstraint k_AutoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(
        Constants.k_sVolts,
        Constants.k_vVoltSecondsPerMeter,
        Constants.k_aVoltSecondsSquaredPerMeter),
    Constants.k_DriveKinematics,
    10);

    public static final TrajectoryConfig config = new TrajectoryConfig(
        Constants.k_MaxSpeedMetersPerSecond,
        Constants.k_MaxAccelerationMetersPerSecondSquared)
    .setKinematics(Constants.k_DriveKinematics)
    .addConstraint(Constants.k_AutoVoltageConstraint);
}