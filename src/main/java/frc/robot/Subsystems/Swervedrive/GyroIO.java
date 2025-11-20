package frc.robot.Subsystems.Swervedrive;

import edu.wpi.first.math.geometry.Rotation2d;

public abstract class GyroIO {
    public abstract Rotation2d getOrientation();
    public abstract void resetOrientation(Rotation2d orientation);
}
