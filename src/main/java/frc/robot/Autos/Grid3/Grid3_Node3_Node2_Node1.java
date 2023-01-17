package frc.robot.Autos.Grid3;

import frc.robot.utilities.AutoPath;
import frc.robot.utilities.AutoRoutine;

public class Grid3_Node3_Node2_Node1 extends AutoRoutine {
    private final AutoPath m_Path1 = new AutoPath("paths/1_Grid3_Node3_Node2_Node1.wpilib.json");
    private final AutoPath m_Path2 = new AutoPath("paths/2_Grid3_Node3_Node2_Node1.wpilib.json");
    private final AutoPath m_Path3 = new AutoPath("paths/3_Grid3_Node3_Node2_Node1.wpilib.json");
    private final AutoPath m_Path4 = new AutoPath("paths/4_Grid3_Node3_Node2_Node1.wpilib.json");
    private final AutoPath[] m_PathList = {
        m_Path1,
        m_Path2,
        m_Path3,
        m_Path4
    };
    
    public Grid3_Node3_Node2_Node1() {
        super("Grid3_Node3_Node2_Node1");
        addCommands(m_PathList);
    }
}
