package frc.robot.Autos.Priority;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;
import frc.robot.utilities.CommandUtililty;

public class Grid2_Node1_Mobility_ChargingStation extends AutoRoutine {
    private AutoPath[] m_PathList;
    private final AutoPath[] m_BlueList = {
        new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation_Blue.wpilib.json")
    };
    public final AutoPath[] m_RedList = {
        new AutoPath("paths/Grid2_Node1_Mobility_ChargingStation_Red.wpilib.json")
    };
    public Grid2_Node1_Mobility_ChargingStation() {
        super("Grid2_Node1_Mobility_ChargingStation");
        if (DriverStation.getAlliance() == Alliance.Blue) {
            m_PathList = m_BlueList;
        } else if (DriverStation.getAlliance() == Alliance.Red) {
            m_PathList = m_RedList;
        } else {
            m_PathList = null;
        }
        addCommands(m_PathList);
        addCommands(CommandUtililty.balanceCommand());
    }
}
