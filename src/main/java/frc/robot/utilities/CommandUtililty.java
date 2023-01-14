package frc.robot.utilities;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public final class CommandUtililty {
    private static final DriveSubsystem m_DriveSubsystem = DriveSubsystem.getInstance();
    private static final VisionSubsystem m_VisionSubsystem = VisionSubsystem.getInstance();

    public static CommandBase driveCommand(DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(() -> m_DriveSubsystem.getDrive().arcadeDrive(
            -forward.getAsDouble(),
            turn.getAsDouble()
        ));
    }

    public static CommandBase balanceCommand() {
        double output = m_DriveSubsystem.getPitchController().calculate(m_DriveSubsystem.getRobotPitch(), 0);
        return new RunCommand(() -> m_DriveSubsystem.tankDrive(output)).until(() -> m_DriveSubsystem.getPitchController().atSetpoint());
    }

    public static Command testAuto() {
        if (m_DriveSubsystem.getRobotPitch() > 0.5) {
            return new RunCommand(() -> m_DriveSubsystem.tankDrive(0.1));
        } else if (m_DriveSubsystem.getRobotPitch() < -0.5) {
            return new RunCommand(() -> m_DriveSubsystem.tankDrive(-0.1));
        } else {
            return new InstantCommand(() -> m_DriveSubsystem.tankDrive(0));
        }
    }

    public static Command conditionalAuto() {
        return new ConditionalCommand(
            new RunCommand(() -> m_DriveSubsystem.tankDrive(0.1)),
            new RunCommand(() -> m_DriveSubsystem.tankDrive(-0.1)),
            () -> (m_DriveSubsystem.getRobotPitch() > 0.5))
        .unless(
            () -> Math.abs(m_DriveSubsystem.getRobotPitch()) < 0.5
        );
    }

    public static Command aprilTagAuto() {
        return new ConditionalCommand(
            new RunCommand(() -> m_DriveSubsystem.tankDrive(0.1)),
            new RunCommand(() -> m_DriveSubsystem.tankDrive(-0.1)),
            () -> (m_VisionSubsystem.getDistanceToGrid() == Constants.k_DistanceFromGridTagToChargingStation))
        .unless(
            () -> (
                Math.abs
                    (m_VisionSubsystem.getDistanceToGrid()) < (Constants.k_DistanceFromGridTagToChargingStation + Units.inchesToMeters(1))
                &&
                Math.abs(
                    m_VisionSubsystem.getDistanceToGrid()) > (Constants.k_DistanceFromGridTagToChargingStation - Units.inchesToMeters(1))
        ));
    }
}
