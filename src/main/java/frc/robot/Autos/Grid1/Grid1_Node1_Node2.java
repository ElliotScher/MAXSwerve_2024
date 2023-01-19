package frc.robot.Autos.Grid1;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;

public class Grid1_Node1_Node2 extends AutoRoutine {
    
    public Grid1_Node1_Node2() {
        super(
            "Grid1_Node1_Node2",
            Constants.k_Grid1_Node1_Node2_Blue,
            Constants.k_Grid1_Node1_Node2_Red
        );
        addCommands(m_PathList);
    }
}