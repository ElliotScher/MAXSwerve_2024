package frc.robot.Autos.Grid1;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid1_Node1_Node2 extends AutoRoutine {
    private final AutoPath m_Path1 = new AutoPath("paths/1_Grid1_Node1_Node2.wpilib.json");
    private final AutoPath m_Path2 = new AutoPath("paths/2_Grid1_Node1_Node2.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1,
        m_Path2
    };
    
    public Grid1_Node1_Node2() {
        super("Grid1_Node1_Node2");
        addCommands(m_PathList);
    }
}
