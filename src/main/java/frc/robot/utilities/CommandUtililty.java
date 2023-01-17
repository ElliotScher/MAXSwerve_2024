package frc.robot.utilities;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public final class CommandUtililty {
    private static final DriveSubsystem m_DriveSubsystem = DriveSubsystem.getInstance();
    private static final VisionSubsystem m_VisionSubsystem = VisionSubsystem.getInstance();

    public static CommandBase driveCommand(DoubleSupplier forward, DoubleSupplier turn) {
        return new RunCommand(
            () -> m_DriveSubsystem.getDrive().arcadeDrive(
                -forward.getAsDouble(),
                turn.getAsDouble()
            )
        );
    }

    public static CommandBase balanceCommand() {
        return new RunCommand(
            () -> m_DriveSubsystem.tankDrive(
                m_DriveSubsystem.getPitchController().calculate(
                    m_DriveSubsystem.getRobotPitch(),
                    0
                )
            )
        ).until(
            () -> m_DriveSubsystem.getPitchController().atSetpoint()
        );
    }

    public static Command conditionalAuto() {
        return new ConditionalCommand(
            new RunCommand(
                () -> m_DriveSubsystem.tankDrive(0.1)),
            new RunCommand(
                () -> m_DriveSubsystem.tankDrive(-0.1)),
            () -> (m_DriveSubsystem.getRobotPitch() > 0.5))
        .unless(
            () -> Math.abs(m_DriveSubsystem.getRobotPitch()) < 0.5
        );
    }

    public static Command aprilTagAuto() {
        return new ConditionalCommand(
            new RunCommand(
                () -> m_DriveSubsystem.tankDrive(0.1)
            ),
            new RunCommand(
                () -> m_DriveSubsystem.tankDrive(-0.1)
            ),
            () -> (m_VisionSubsystem.getChargingStationPitch() > 0)
        ).unless(
            () -> (Math.abs(m_VisionSubsystem.getChargingStationPitch()) < 0.5)
        );
    }
}
