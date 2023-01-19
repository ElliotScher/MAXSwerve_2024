package frc.robot.utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutine extends SequentialCommandGroup {
    protected AutoPath[] m_PathList;
    private String m_Name;

    public AutoRoutine(String name, AutoPath[] blueList, AutoPath[] redList) {
        m_Name = name;
        // if fms says blue alliance {
        //     m_PathList = blueList
        // } else {
        //     m_PathList = redList
        // }
    }

    public void addCommands(AutoPath[] pathList) {
        int i = 0;
        for (AutoPath path: pathList) {
            addCommands(
                path.getCommand()
            );
            m_PathList[i] = path;
            i++;
        }
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