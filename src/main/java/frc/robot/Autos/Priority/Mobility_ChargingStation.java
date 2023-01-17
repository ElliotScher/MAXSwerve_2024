package frc.robot.Autos.Priority;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Mobility_ChargingStation extends AutoRoutine {
    private final AutoPath m_Path1 = new AutoPath("paths/Mobility_ChargingStation.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1
    };
    
    public Mobility_ChargingStation() {
        super("Mobility_ChargingStation");
        addCommands(m_PathList);
    }
}
