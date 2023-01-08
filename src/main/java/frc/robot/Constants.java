// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
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

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 2;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    public static final double ksVolts = 0.08134;
    public static final double kvVoltSecondsPerMeter = 2.0181;
    public static final double kaVoltSecondsSquaredPerMeter = 0.36127;

    public static final double kPDriveVel = 0.0060339;

    public static final double kTrackwidthMeters = Units.inchesToMeters(22);
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(
        Constants.ksVolts,
        Constants.kvVoltSecondsPerMeter,
        Constants.kaVoltSecondsSquaredPerMeter),
    Constants.kDriveKinematics,
    10);

    public static final TrajectoryConfig config = new TrajectoryConfig(
        Constants.kMaxSpeedMetersPerSecond,
        Constants.kMaxAccelerationMetersPerSecondSquared)
    .setKinematics(Constants.kDriveKinematics)
    .addConstraint(Constants.autoVoltageConstraint);

    public static final PIDController k_BalanceController = new PIDController(0, 0, 0);
}