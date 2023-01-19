package frc.robot.Autos.Grid3;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid3_Node3_Intake_ChargingStation extends AutoRoutine{

    public Grid3_Node3_Intake_ChargingStation() {
        super(
            "Grid3_Node3_Intake_ChargingStation",
            Constants.k_Grid3_Node3_Intake_ChargingStation_Blue,
            Constants.k_Grid3_Node3_Intake_ChargingStation_Red
        );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}