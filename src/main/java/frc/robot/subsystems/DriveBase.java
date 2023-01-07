// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {
    private static DriveBase m_Instance;

    private CANSparkMax m_LeftLeader;
    private CANSparkMax m_RightLeader;
    private CANSparkMax m_LeftFollower;
    private CANSparkMax m_RightFollower;

    private ADXRS450_Gyro m_Gyro;

    private DifferentialDrive m_Drive;
    private DifferentialDriveOdometry m_Odometry;
  
    public DriveBase() {
        m_LeftLeader = new CANSparkMax(1, MotorType.kBrushless);
        m_RightLeader = new CANSparkMax(2, MotorType.kBrushless);

        m_LeftFollower = new CANSparkMax(3, MotorType.kBrushless);
        m_RightFollower = new CANSparkMax(4, MotorType.kBrushless);

        m_LeftFollower.follow(m_LeftLeader);
        m_RightFollower.follow(m_RightLeader);

        m_LeftLeader.setIdleMode(IdleMode.kBrake);
        m_RightLeader.setIdleMode(IdleMode.kBrake);
        m_LeftFollower.setIdleMode(IdleMode.kBrake);
        m_RightFollower.setIdleMode(IdleMode.kBrake);

        m_Drive = new DifferentialDrive(m_LeftLeader, m_RightLeader);

        m_Gyro = new ADXRS450_Gyro();
        m_Gyro.calibrate();

        m_LeftLeader.getEncoder().setPositionConversionFactor(Constants.POSITION_CONVERSION_FACTOR);
        m_RightLeader.getEncoder().setPositionConversionFactor(Constants.POSITION_CONVERSION_FACTOR);
        m_LeftLeader.getEncoder().setVelocityConversionFactor(Constants.VELOCITY_CONVERSION_FACTOR);
        m_RightLeader.getEncoder().setVelocityConversionFactor(Constants.VELOCITY_CONVERSION_FACTOR);

        m_Odometry = new DifferentialDriveOdometry(m_Gyro.getRotation2d(), m_LeftLeader.getEncoder().getPosition(), m_RightLeader.getEncoder().getPosition());

        resetEncoders();
        resetGyro();
    }

    @Override
    public void periodic() {
        m_Odometry.update(
            m_Gyro.getRotation2d(),
            m_LeftLeader.getEncoder().getPosition(),
            m_RightLeader.getEncoder().getPosition()
        );
    }

    public CommandBase driveCommand(DoubleSupplier forward, DoubleSupplier turn) {
        return run(
            () -> {
                m_Drive.arcadeDrive(
                    forward.getAsDouble(),
                    turn.getAsDouble()
                );
            });
      }

      public DifferentialDrive getDrive() {
        return m_Drive;
    }

    public Pose2d getPose() {
        return m_Odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_LeftLeader.getEncoder().getVelocity(), m_RightLeader.getEncoder().getVelocity());
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        resetGyro();
        m_Odometry.resetPosition(m_Gyro.getRotation2d(), m_LeftLeader.getEncoder().getPosition(), m_RightLeader.getEncoder().getPosition(), pose);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_LeftLeader.setVoltage(leftVolts);
        m_RightLeader.setVoltage(rightVolts);
        m_Drive.feed();
    }

    public void resetEncoders() {
        m_LeftLeader.getEncoder().setPosition(0);
        m_RightLeader.getEncoder().setPosition(0);
    }

    public double getAverageEncoderDistance() {
        return (m_LeftLeader.getEncoder().getPosition() + m_RightLeader.getEncoder().getPosition()) / 2;
    }

    public void resetGyro() {
        m_Gyro.reset();
    }

    public void calibrateGyro() {
        m_Gyro.calibrate();
    }

    public double getHeading() {
        return m_Gyro.getRotation2d().getDegrees();
    }

    public double getTurnRate() {
        return -m_Gyro.getRate();
    }

        
    public static DriveBase getInstance() {
        if (m_Instance == null) {
            m_Instance = new DriveBase();
        }
        return m_Instance;
    }
}
