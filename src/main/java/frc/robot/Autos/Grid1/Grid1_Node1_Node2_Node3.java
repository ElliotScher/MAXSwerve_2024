package frc.robot.Autos.Grid1;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid1_Node1_Node2_Node3 extends AutoRoutine {
    private final AutoPath m_Path1 = new AutoPath("paths/1_Grid1_Node1_Node2_Node3.wpilib.json");
    private final AutoPath m_Path2 = new AutoPath("paths/2_Grid1_Node1_Node2_Node3.wpilib.json");
    private final AutoPath m_Path3 = new AutoPath("paths/3_Grid1_Node1_Node2_Node3.wpilib.json");
    private final AutoPath m_Path4 = new AutoPath("paths/4_Grid1_Node1_Node2_Node3.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1,
        m_Path2,
        m_Path3,
        m_Path4
    };
    
    public Grid1_Node1_Node2_Node3() {
        super("Grid1_Node1_Node2_Node3");
        addCommands(m_PathList);
    }
}
