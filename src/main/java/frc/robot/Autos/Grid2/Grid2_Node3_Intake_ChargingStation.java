package frc.robot.Autos.Grid2;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid2_Node3_Intake_ChargingStation extends AutoRoutine{
    private final AutoPath m_Path1 = new AutoPath("paths/1_Grid2_Node3_Intake_ChargingStation.wpilib.json");
    private final AutoPath m_Path2 = new AutoPath("paths/2_Grid2_Node3_Intake_ChargingStation.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1,
        m_Path2
    };
    
    public Grid2_Node3_Intake_ChargingStation() {
        super("Grid2_Node3_Intake_ChargingStation");
        addCommands(m_PathList);
    }
}