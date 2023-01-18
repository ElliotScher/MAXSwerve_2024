package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TelevatorSubsystem extends SubsystemBase {
    private CANSparkMax m_ElevatorLeader;
    private CANSparkMax m_ElevatorFollower;
    private CANSparkMax m_Telescope;

    private RelativeEncoder m_ElevatorEncoder;
    private RelativeEncoder m_TelescopeEncoder;

    private static TelevatorSubsystem m_Instance;

    public TelevatorSubsystem() {
        m_ElevatorLeader = new CANSparkMax(Constants.k_ElevatorLeaderID, MotorType.kBrushless);
        m_ElevatorFollower = new CANSparkMax(Constants.k_ElevatorFollowerID, MotorType.kBrushless);

        m_ElevatorFollower.follow(m_ElevatorLeader);

        m_ElevatorEncoder = m_ElevatorLeader.getEncoder();
        m_TelescopeEncoder = m_Telescope.getEncoder();

        m_Telescope = new CANSparkMax(Constants.k_TelescopeID, MotorType.kBrushless);
    }

    public void moveYAxis(double speed) {
        m_ElevatorLeader.set(speed);
    }

    public void moveXAxis(double speed) {
        m_Telescope.set(speed);
    }

    public void elevatorLowNode() {
        if (m_ElevatorEncoder.getPosition() < Constants.k_ElevatorLowNodeSetpoint - 1) {
            m_ElevatorLeader.set(1);
        } else if (m_ElevatorEncoder.getPosition() > Constants.k_ElevatorLowNodeSetpoint + 1) {
            m_ElevatorLeader.set(-1);
        } else {
            m_ElevatorLeader.set(0);
        }
    }

    public void elevatorMidNode() {
        if (m_ElevatorEncoder.getPosition() < Constants.k_ElevatorMidNodeSetpoint - 1) {
            m_ElevatorLeader.set(1);
        } else if (m_ElevatorEncoder.getPosition() > Constants.k_ElevatorMidNodeSetpoint + 1) {
            m_ElevatorLeader.set(-1);
        } else {
            m_ElevatorLeader.set(0);
        }
    }

    public void elevatorTopNode() {
        if (m_ElevatorEncoder.getPosition() < Constants.k_ElevatorTopNodeSetpoint - 1) {
            m_ElevatorLeader.set(1);
        } else if (m_ElevatorEncoder.getPosition() > Constants.k_ElevatorTopNodeSetpoint + 1) {
            m_ElevatorLeader.set(-1);
        } else {
            m_ElevatorLeader.set(0);
        }
    }

    public void elevatorReset() {
        if (m_ElevatorEncoder.getPosition() < -1) {
            m_ElevatorLeader.set(1);
        } else if (m_ElevatorEncoder.getPosition() > 1) {
            m_ElevatorLeader.set(-1);
        } else {
            m_ElevatorLeader.set(0);
        }
    }

    public void telescopeLowNode() {
        if (m_TelescopeEncoder.getPosition() < Constants.k_ElevatorLowNodeSetpoint - 1) {
            m_Telescope.set(1);
        } else if (m_TelescopeEncoder.getPosition() > Constants.k_ElevatorLowNodeSetpoint + 1) {
            m_Telescope.set(-1);
        } else {
            m_Telescope.set(0);
        }
    }

    public void telescopeMidNode() {
        if (m_TelescopeEncoder.getPosition() < Constants.k_ElevatorMidNodeSetpoint - 1) {
            m_Telescope.set(1);
        } else if (m_TelescopeEncoder.getPosition() > Constants.k_ElevatorMidNodeSetpoint + 1) {
            m_Telescope.set(-1);
        } else {
            m_Telescope.set(0);
        }
    }

    public void telescopeTopNode() {
        if (m_TelescopeEncoder.getPosition() < Constants.k_ElevatorTopNodeSetpoint - 1) {
            m_Telescope.set(1);
        } else if (m_TelescopeEncoder.getPosition() > Constants.k_ElevatorTopNodeSetpoint + 1) {
            m_Telescope.set(-1);
        } else {
            m_Telescope.set(0);
        }
    }

    public void telescopeReset() {
        if (m_TelescopeEncoder.getPosition() < -1) {
            m_Telescope.set(1);
        } else if (m_TelescopeEncoder.getPosition() > 1) {
            m_Telescope.set(-1);
        } else {
            m_Telescope.set(0);
        }
    }

    public static TelevatorSubsystem getInstance() {
        if (m_Instance == null) {
            m_Instance = new TelevatorSubsystem();
        }
        return m_Instance;
    }
}