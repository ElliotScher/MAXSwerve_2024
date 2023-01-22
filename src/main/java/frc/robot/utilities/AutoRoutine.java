package frc.robot.utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutine extends SequentialCommandGroup {
    private AutoPath[] m_PathList;
    private String m_Name;

    public AutoRoutine(String name) {
        m_Name = name;
    }

    public void addCommands(AutoPath[] pathList) {
        for (AutoPath path: pathList) {
            addCommands(
                path.getCommand()
            );
        }
        m_PathList = pathList;
    }
    
    public Pose2d getInitialPose() {
        return m_PathList[0].getInitialPose();
    }

    public AutoPath[] getTrajectories() {
        return m_PathList;
    }

    public String getName() {
        return m_Name;
    }
}