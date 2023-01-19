package frc.robot.Autos.Priority;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class ChargingStation extends AutoRoutine {
    
    public ChargingStation() {
        super(
            "ChargingStation",
            Constants.k_ChargingStation_Blue,
            Constants.k_ChargingStation_Red
        );
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}
