package frc.robot.Autos.Priority;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Mobility_ChargingStation extends AutoRoutine {

    public Mobility_ChargingStation() {
        super(
            "Mobility_ChargingStation",
            Constants.k_Mobility_ChargingStation_Blue,
            Constants.k_Mobility_ChargingStation_Red
        );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}
