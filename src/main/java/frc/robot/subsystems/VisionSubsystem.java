package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VisionSubsystem extends SubsystemBase {
    private static final PhotonCamera m_Camera = new PhotonCamera("Lime1");
    private static PhotonPipelineResult m_Result;

    private static VisionSubsystem m_Instance;

    public VisionSubsystem() {}

    @Override
    public void periodic() {
        m_Result = m_Camera.getLatestResult();
    }

    public double getDistanceToGrid() {
        double distance = -1;
        if (m_Result.hasTargets()) {
            PhotonTrackedTarget target = m_Result.getBestTarget();
            if (target.getFiducialId() == 7 || target.getFiducialId() == 2) {
                distance = PhotonUtils.calculateDistanceToTargetMeters(
                    Constants.k_CameraHeightMeters,
                    Constants.k_GridAprilTagHeightMeters,
                    Constants.k_CameraPitchRadians,
                    Units.degreesToRadians(
                        m_Result.getBestTarget().getPitch()
                    )
                );
            }
        }
        System.out.println(distance);
        return distance;
    }

    public double getDistanceToGridDynamicPitch() {
        double distance = -1;
        if (m_Result.hasTargets()) {
            PhotonTrackedTarget target = m_Result.getBestTarget();
            if (target.getFiducialId() == 7 || target.getFiducialId() == 2) {
                distance = PhotonUtils.calculateDistanceToTargetMeters(
                    Constants.k_CameraHeightMeters,
                    Constants.k_GridAprilTagHeightMeters,
                    getChargingStationPitch(),
                    Units.degreesToRadians(
                        m_Result.getBestTarget().getPitch()
                    )
                );
            }
        }
        System.out.println(distance);
        return distance;
    }

    public double getChargingStationPitch() {
        if (m_Result.hasTargets()) {
            PhotonTrackedTarget target = m_Result.getBestTarget();
            if (target.getFiducialId() == 7 || target.getFiducialId() == 2) {
                return target.getPitch();
            }
        }
        return -1;
    }
    

    public static VisionSubsystem getInstance() {
        if (m_Instance == null) {
            m_Instance = new VisionSubsystem();
        }
        return m_Instance;
    }
}