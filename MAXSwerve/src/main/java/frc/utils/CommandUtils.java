package frc.utils;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;

public class CommandUtils {
    public static RunCommand getDriveCommand(double lefty, double leftx, double rightx) {
        return new RunCommand(
            () -> DriveSubsystem.getInstance().drive(
                -MathUtil.applyDeadband(lefty, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(leftx, OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(rightx, OIConstants.kDriveDeadband),
                true,
                true
            ),
            DriveSubsystem.getInstance()
        );
    }
}