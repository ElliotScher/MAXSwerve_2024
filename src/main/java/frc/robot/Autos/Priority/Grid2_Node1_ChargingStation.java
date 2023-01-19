package frc.robot.Autos.Priority;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid2_Node1_ChargingStation extends AutoRoutine {
 
    public Grid2_Node1_ChargingStation() {
        super(
            "Grid2_Node1_ChargingStation",
            Constants.k_Grid2_Node1_ChargingStation_Blue,
            Constants.k_Grid2_Node1_ChargingStation_Red
        );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}
