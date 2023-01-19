package frc.robot.Autos.Grid1;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid1_Node1_Intake_ChargingStation extends AutoRoutine{

    public Grid1_Node1_Intake_ChargingStation() {
        super(
            "Grid1_Node1_Intake_ChargingStation",
            Constants.k_Grid1_Node1_Intake_ChargingStation_Blue,
            Constants.k_Grid1_Node1_Intake_ChargingStation_Red
        );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}