package frc.robot.Autos.Priority;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid2_Node1_Mobility_ChargingStation extends AutoRoutine {
    private final AutoPath mPath1 = new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation.wpilib.json");
    private final AutoPath[] mPathList = {mPath1};
    
    public Grid2_Node1_Mobility_ChargingStation() {
        super("Grid2_Node1_Mobility_ChargingStation");
        addCommands(mPathList);
    }
}