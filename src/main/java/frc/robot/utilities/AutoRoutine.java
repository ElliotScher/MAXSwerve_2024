package frc.robot.utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutine extends SequentialCommandGroup {
    protected AutoPath[] m_PathList;
    private String m_Name;

    public AutoRoutine(String name, AutoPath[] blueList, AutoPath[] redList) {
        m_Name = name;
        if (DriverStation.getAlliance() == Alliance.Blue) {
            m_PathList = blueList;
        } else if (DriverStation.getAlliance() == Alliance.Red) {
            m_PathList = redList;
        } else {
            m_PathList = null;
        }
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