package frc.robot.utilities;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TelevatorSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public final class CommandUtililty {
    private static final DriveSubsystem m_DriveSubsystem = DriveSubsystem.getInstance();
    private static final VisionSubsystem m_VisionSubsystem = VisionSubsystem.getInstance();
    private static final TelevatorSubsystem m_TelevatorSubsystem = TelevatorSubsystem.getInstance();

    public static Command driveCommand(DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> m_DriveSubsystem.getDrive().arcadeDrive(
                -turn.getAsDouble(),
                forward.getAsDouble()
            )
        );
    }

    public static Command balanceCommand() {
        return new RunCommand(
            () -> m_DriveSubsystem.tankDrive(
                m_DriveSubsystem.getPitchController().calculate(
                    -m_DriveSubsystem.getRobotPitch(),
                    0
                )
            )
        ).until(
            () -> m_DriveSubsystem.getPitchController().atSetpoint()
        );
    }

    public static Command aprilTagAuto() {
        return new RunCommand(
            () -> m_DriveSubsystem.tankDrive(
                m_DriveSubsystem.getAprilTagDistanceController().calculate(
                    m_VisionSubsystem.getDistanceToGrid(),
                    Constants.k_DistanceFromGridTagToChargingStation
                )
            )
        ).until(
            () -> m_DriveSubsystem.getPitchController().atSetpoint()
        ).unless(
            () -> Math.abs(m_DriveSubsystem.getRobotPitch()) <= 2.5
        );
    }

    public static Command aprilTagAutoNoGyroHelp() {
        return new RunCommand(
            () -> m_DriveSubsystem.tankDrive(
                m_DriveSubsystem.getAprilTagDistanceController().calculate(
                    m_VisionSubsystem.getDistanceToGrid(),
                    Constants.k_DistanceFromGridTagToChargingStation
                )
            )
        ).until(
            () -> m_DriveSubsystem.getPitchController().atSetpoint()
        );
    }

    public static Command autoCommand(AutoRoutine auto) {
        return new InstantCommand(
            () -> m_DriveSubsystem.resetOdometry(
                auto.getInitialPose()
            )
        )
        .andThen(
            auto
        );
    }

    public static Command moveX(double speed) {
        return new InstantCommand(
            () -> m_TelevatorSubsystem.moveXAxis(speed)
        );
    }

    public static Command moveY(double speed) {
        return new InstantCommand(
            () -> m_TelevatorSubsystem.moveYAxis(speed)
        );
    }

    public static Command lowNode() {
        return new ParallelCommandGroup(
            new RunCommand(
                () -> m_TelevatorSubsystem.elevatorLowNode()
            ),
            new RunCommand(
                () -> m_TelevatorSubsystem.telescopeLowNode()
            )
        );
    }

    public static Command midNode() {
        return new ParallelCommandGroup(
            new RunCommand(
                () -> m_TelevatorSubsystem.elevatorMidNode()
            ),
            new RunCommand(
                () -> m_TelevatorSubsystem.telescopeMidNode()
            )
        );
    }

    public static Command topNode() {
        return new ParallelCommandGroup(
            new RunCommand(
                () -> m_TelevatorSubsystem.elevatorTopNode()
            ),
            new RunCommand(
                () -> m_TelevatorSubsystem.telescopeTopNode()
            )
        );
    }

    public static Command resetTelevator() {
        return new ParallelCommandGroup(
            new RunCommand(
                () -> m_TelevatorSubsystem.elevatorReset()
            ),
            new RunCommand(
                () -> m_TelevatorSubsystem.telescopeReset()
            )
        );
    }
}
