package frc.robot.Subsystems.Swervedrive;

public interface ModuleIO {
    abstract double getSpeed();
    abstract double getOrientation();
    abstract void setSpeed(double speed);
    abstract void setOrientation(double orientation);
    abstract void setOrientationOffset(double offset);
}