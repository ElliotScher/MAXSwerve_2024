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
import frc.robot.subsystems.DriveSubsystem;

public class AutoPath {
    private final DriveSubsystem m_DriveBase = DriveSubsystem.getInstance();
    private Trajectory m_Trajectory;
    private Command m_Command;

    public AutoPath(String jsonPath) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            m_Trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
        }

        m_Command = new RamseteCommand(
            m_Trajectory,
            DriveSubsystem.getInstance()::getPose,
            new RamseteController(
                Constants.k_RamseteB,
                Constants.k_RamseteZeta
            ),
            new SimpleMotorFeedforward(
                Constants.k_sVolts,
                Constants.k_vVoltSecondsPerMeter,
                Constants.k_aVoltSecondsSquaredPerMeter
            ),
            Constants.k_DriveKinematics,
            DriveSubsystem.getInstance()::getWheelSpeeds,
            new PIDController(
                Constants.k_pDriveVel,
                0,
                0
            ),
            new PIDController(
                Constants.k_pDriveVel,
                0,
                0
            ),
            DriveSubsystem.getInstance()::tankDriveVolts,
            DriveSubsystem.getInstance()
        );
    }

    public Command getCommand() {
        return m_Command.andThen(
            () -> m_DriveBase.tankDriveVolts(
                0,
                0
            )
        );
    }

    public Pose2d getInitialPose() {
        return m_Trajectory.getInitialPose();
    }

    public Trajectory getTrajectory() {
        return m_Trajectory;
    }
}