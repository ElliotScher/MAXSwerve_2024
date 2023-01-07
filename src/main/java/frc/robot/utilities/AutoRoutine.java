package frc.robot.utilities;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutine extends SequentialCommandGroup {
    
    private ArrayList<AutoPath> mPathList;

    public AutoRoutine() {
        mPathList = new ArrayList<AutoPath>();
    }

    public void addCommands(AutoPath[] pathList) {
        for (AutoPath path: pathList) {
            addCommands(path.getCommand(true));
            mPathList.add(path);
        }
    }
    
    public Pose2d getInitialPose() {
        return mPathList.get(0).getInitialPose();
    }
}