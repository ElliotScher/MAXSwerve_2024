package frc.robot.utilities;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutine extends SequentialCommandGroup {
    private ArrayList<AutoPath> m_PathList;
    private String m_Name;

    public AutoRoutine(String name) {
        m_Name = name;
        m_PathList = new ArrayList<AutoPath>();
    }

    public void addCommands(AutoPath[] pathList) {
        for (AutoPath path: pathList) {
            addCommands(
                path.getCommand()
            );
            m_PathList.add(
                path
            );
        }
    }
    
    public Pose2d getInitialPose() {
        return m_PathList.get(0).getInitialPose();
    }

    public ArrayList<AutoPath> getTrajectories() {
        return m_PathList;
    }

    public String getName() {
        return m_Name;
    }
}