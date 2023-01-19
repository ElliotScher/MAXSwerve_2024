package frc.robot.Autos.Grid1;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;

public class Grid1_Node1_Node2_Node3 extends AutoRoutine {

    public Grid1_Node1_Node2_Node3() {
        super(
            "Grid1_Node1_Node2_Node3",
            Constants.k_Grid1_Node1_Node2_Node3_Blue,
            Constants.k_Grid1_Node1_Node2_Node3_Red
        );
        addCommands(m_PathList);
    }
}
