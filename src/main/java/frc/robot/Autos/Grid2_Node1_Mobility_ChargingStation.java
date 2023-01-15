package frc.robot.Autos;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid2_Node1_Mobility_ChargingStation extends AutoRoutine {
    private final AutoPath mPath1 = new AutoPath("paths/1_Grid2_Node1_Mobility_ChargingStation.wpilib.json");
    private final AutoPath mPath2 = new AutoPath("paths/2_Grid2_Node1_Mobility_ChargingStation.wpilib.json");
    private final AutoPath[] mPathList = {mPath1, mPath2};
    
    public Grid2_Node1_Mobility_ChargingStation() {
        super("Grid2_Node1_Mobility_ChargingStation");
        addCommands(mPathList);
    }
}
