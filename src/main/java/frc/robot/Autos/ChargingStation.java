package frc.robot.Autos;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class ChargingStation extends AutoRoutine {
    private final AutoPath mPath1 = new AutoPath("paths/ChargingStation.wpilib.json");
    private final AutoPath[] mPathList = {mPath1};
    
    public ChargingStation() {
        addCommands(mPathList);
    }
}
