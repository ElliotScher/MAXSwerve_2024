package frc.robot.Autos;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Mobility_ChargingStation extends AutoRoutine {
    private final AutoPath mPath1 = new AutoPath("paths/Mobility_ChargingStation.wpilib.json");
    private final AutoPath[] mPathList = {
        mPath1
    };
    
    public Mobility_ChargingStation() {
        super("Mobility_ChargingStation");
        addCommands(mPathList);
    }
}
