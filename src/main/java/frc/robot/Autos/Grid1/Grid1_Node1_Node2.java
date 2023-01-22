package frc.robot.Autos.Grid1;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid1_Node1_Node2 extends AutoRoutine {
    private AutoPath[] m_PathList;
    private final AutoPath[] m_BlueList = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Blue.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Blue.wpilib.json")
    };
    public final AutoPath[] m_RedList = {
        new AutoPath("paths/1_Grid1_Node1_Node2_Red.wpilib.json"),
        new AutoPath("paths/2_Grid1_Node1_Node2_Red.wpilib.json")
    };
    public Grid1_Node1_Node2() {
        super("Grid1_Node1_Node2");
        if (DriverStation.getAlliance() == Alliance.Blue) {
            m_PathList = m_BlueList;
        } else if (DriverStation.getAlliance() == Alliance.Red) {
            m_PathList = m_RedList;
        } else {
            m_PathList = null;
        }
        addCommands(m_PathList);
    }
}