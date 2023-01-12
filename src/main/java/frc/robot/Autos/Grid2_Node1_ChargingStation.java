package frc.robot.Autos;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid2_Node1_ChargingStation extends AutoRoutine {
    private final AutoPath mPath1 = new AutoPath("paths/Grid2_Node1_ChargingStation.wpilib.json");
    private final AutoPath[] mPathList = {mPath1};
    
    public Grid2_Node1_ChargingStation() {
        addCommands(mPathList);
    }
}
