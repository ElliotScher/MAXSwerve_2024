package frc.robot.utilities;
import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

public class AutoPath {
    private final DriveBase mDriveBase = DriveBase.getInstance();
    private Trajectory mTrajectory;
    private Command mCommand;
    
    public AutoPath(String jsonPath) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
        }

        mCommand = new RamseteCommand(
            mTrajectory,
            DriveBase.getInstance()::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            DriveBase.getInstance()::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            DriveBase.getInstance()::tankDriveVolts,
            DriveBase.getInstance()
        );
    }

    public AutoPath(String jsonPath, Command additionalCommand) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
        }

        mCommand = new RamseteCommand(
            mTrajectory,
            DriveBase.getInstance()::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            DriveBase.getInstance()::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            DriveBase.getInstance()::tankDriveVolts,
            DriveBase.getInstance()
        )
        .alongWith(additionalCommand);
    }

    public AutoPath(String jsonPath, Command beforeStarting, Command additionalCommand) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
        }

        mCommand = new RamseteCommand(
            mTrajectory,
            DriveBase.getInstance()::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            DriveBase.getInstance()::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            DriveBase.getInstance()::tankDriveVolts,
            DriveBase.getInstance()
        )
        .alongWith(additionalCommand)
        .beforeStarting(beforeStarting);
    }

    public AutoPath(String jsonPath, Command beforeStarting, Command additionalCommand, Command onCompletion) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
        }

        mCommand = new RamseteCommand(
            mTrajectory,
            DriveBase.getInstance()::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            DriveBase.getInstance()::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            DriveBase.getInstance()::tankDriveVolts,
            DriveBase.getInstance()
        )
        .alongWith(additionalCommand)
        .beforeStarting(beforeStarting)
        .andThen(onCompletion);
    }

    public Command getCommand(Boolean decorated) {
        return mCommand.andThen(() -> mDriveBase.tankDriveVolts(0, 0));
    }

    public Pose2d getInitialPose() {
        return mTrajectory.getInitialPose();
    }
}