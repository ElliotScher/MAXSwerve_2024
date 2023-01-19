package frc.robot.Autos.Grid2;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid2_Node1_Intake_ChargingStation extends AutoRoutine{

    public Grid2_Node1_Intake_ChargingStation() {
        super(
            "Grid2_Node1_Intake_ChargingStation",
            Constants.k_Grid2_Node1_Intake_ChargingStation_Blue,
            Constants.k_Grid2_Node1_Intake_ChargingStation_Red
            );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}