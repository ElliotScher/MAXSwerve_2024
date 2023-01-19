package frc.robot.Autos.Grid3;

import frc.robot.Constants;
import frc.robot.utilities.AutoRoutine;

public class Grid3_Node3_Node2 extends AutoRoutine {

    public Grid3_Node3_Node2() {
        super(
            "Grid3_Node3_Node2",
            Constants.k_Grid3_Node3_Node2_Blue,
            Constants.k_Grid3_Node3_Node2_Red
        );
        addCommands(m_PathList);
    }
}