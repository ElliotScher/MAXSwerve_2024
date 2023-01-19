package frc.robot.Autos.Grid3;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;

public class Grid3_Node3_Node2_Node1 extends AutoRoutine {

    public Grid3_Node3_Node2_Node1() {
        super(
            "Grid3_Node3_Node2_Node1",
            Constants.k_Grid3_Node3_Node2_Node1_Blue,
            Constants.k_Grid3_Node3_Node2_Node1_Red
        );
        addCommands(m_PathList);
    }
}
