package frc.robot.Subsystems.Swervedrive;

import edu.wpi.first.math.geometry.Rotation2d;

public class Pidgeon2GyroIO implements GyroIO {

    @Override
    public Rotation2d getOrientation() {
        return new Rotation2d(0.06, 0.07);
    }

    @Override
    public void resetOrientation(Rotation2d orientation){};
}
