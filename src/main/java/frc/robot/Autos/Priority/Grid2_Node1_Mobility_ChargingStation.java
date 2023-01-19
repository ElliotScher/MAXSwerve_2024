package frc.robot.Autos.Priority;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid2_Node1_Mobility_ChargingStation extends AutoRoutine {
    private final AutoPath m_Path1 = new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1,
    };
    
    public Grid2_Node1_Mobility_ChargingStation() {
        super("Grid2_Node1_Mobility_ChargingStation");
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}
