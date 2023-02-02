// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    private static DriveSubsystem m_Instance;

    private CANSparkMax m_LeftLeader;
    private CANSparkMax m_RightLeader;
    private CANSparkMax m_LeftFollower;
    private CANSparkMax m_RightFollower;

    private ADIS16470_IMU m_IMU;
    private PIDController m_PitchController;
    private PIDController m_AprilTagDistanceController;

    private DifferentialDrive m_Drive;
    private DifferentialDriveOdometry m_Odometry;


    public DriveSubsystem() {
        m_LeftLeader = new CANSparkMax(
            Constants.k_LeftDriveLeaderID,
            MotorType.kBrushless
        );

        m_RightLeader = new CANSparkMax(
            Constants.k_RightDriveLeaderID,
            MotorType.kBrushless
        );
        m_LeftFollower = new CANSparkMax(
            Constants.k_LeftDriveFollower1ID,
            MotorType.kBrushless
        );

        m_RightFollower = new CANSparkMax(
            Constants.k_RightDriveFollower1ID,
            MotorType.kBrushless
        );

        m_LeftFollower.follow(m_LeftLeader);
        m_RightFollower.follow(m_RightLeader);

        m_LeftLeader.setInverted(true);
        m_LeftFollower.setInverted(true);

        m_LeftLeader.getEncoder().setPositionConversionFactor(Constants.k_PositionConversionFactor);
        m_RightLeader.getEncoder().setPositionConversionFactor(Constants.k_PositionConversionFactor);
        m_LeftLeader.getEncoder().setVelocityConversionFactor(Constants.k_VelocityConversionFactor);
        m_RightLeader.getEncoder().setVelocityConversionFactor(Constants.k_VelocityConversionFactor);

        m_LeftLeader.setIdleMode(IdleMode.kBrake);
        m_RightLeader.setIdleMode(IdleMode.kBrake);
        m_LeftFollower.setIdleMode(IdleMode.kBrake);
        m_RightFollower.setIdleMode(IdleMode.kBrake);

        m_IMU = new ADIS16470_IMU();
        m_IMU.calibrate();

        m_PitchController = new PIDController(
            Constants.k_pPitch,
            Constants.k_iPitch,
            Constants.k_dPitch
        );

        m_AprilTagDistanceController = new PIDController(
            Constants.k_PDistance,
            Constants.k_IDistance,
            Constants.k_DDistance
        );

        m_PitchController.setTolerance(
            2.5,
            0.5
        );

        m_Drive = new DifferentialDrive(
            m_LeftLeader,
            m_RightLeader
        );
        m_Drive.setSafetyEnabled(false);
        m_Odometry = new DifferentialDriveOdometry(
            Rotation2d.fromDegrees(getHeading()),
            m_LeftLeader.getEncoder().getPosition(),
            m_RightLeader.getEncoder().getPosition()
        );
        resetEncoders();
        resetGyro();
    }

    @Override
    public void periodic() {
        m_Odometry.update(
            Rotation2d.fromDegrees(getHeading()),
            m_LeftLeader.getEncoder().getPosition(),
            m_RightLeader.getEncoder().getPosition()
        );
    }


    public Pose2d getPose() {
        return m_Odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
            m_LeftLeader.getEncoder().getVelocity(),
            m_RightLeader.getEncoder().getVelocity()
        );
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        resetGyro();
        m_Odometry.resetPosition(
            new Rotation2d(
                Units.degreesToRadians(getHeading())
            ),
            m_LeftLeader.getEncoder().getPosition(),
            m_RightLeader.getEncoder().getPosition(),
            pose
        );
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_LeftLeader.setVoltage(leftVolts);
        m_RightLeader.setVoltage(rightVolts);
        m_Drive.feed();
    }

    public void tankDrive(double speed) {
        m_LeftLeader.set(-speed / RobotController.getInputVoltage());
        m_RightLeader.set(speed / RobotController.getInputVoltage());
        m_Drive.feed();
    }

    public void tankDrive(double left, double right) {
        m_LeftLeader.set(left / RobotController.getInputVoltage());
        m_RightLeader.set(right / RobotController.getInputVoltage());
        m_Drive.feed();
    }

    public void resetEncoders() {
        m_LeftLeader.getEncoder().setPosition(0);
        m_RightLeader.getEncoder().setPosition(0);
    }

    public void resetGyro() {
        m_IMU.reset();
    }

    public void calibrateGyro() {
        m_IMU.calibrate();
    }

    public double getHeading() {
        return -m_IMU.getAngle() % 360;
    }

    public double getTurnRate() {
        return -m_IMU.getRate();
    }

    public double getRobotPitch() {
        return m_IMU.getYComplementaryAngle();
    }

    public DifferentialDrive getDrive() {
        return m_Drive;
    }

    public PIDController getPitchController() {
        return m_PitchController;
    }

    public PIDController getAprilTagDistanceController() {
        return m_AprilTagDistanceController;
    }

    public void run(Command command) {
        run(command);
    }

    public static DriveSubsystem getInstance() {
        if (m_Instance == null) {
            m_Instance = new DriveSubsystem();
        }
        return m_Instance;
    }
}