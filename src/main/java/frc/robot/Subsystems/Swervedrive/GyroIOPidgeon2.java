package frc.robot.Subsystems.Swervedrive;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.RobotMap;

public class GyroIOPidgeon2 implements GyroIO {
    
    private final Pigeon2 pigeon = new Pigeon2(RobotMap.GYRO_ID);

    public GyroIOPidgeon2() {
        pigeon.getConfigurator().apply(new Pigeon2Configuration());
        pigeon.getConfigurator().setYaw(0.0);
        pigeon.optimizeBusUtilization();
    }

    @Override
    public Rotation2d getOrientation() {
        return new Rotation2d(pigeon.getYaw().getValueAsDouble());
    }

    @Override
    public void resetOrientation(Rotation2d orientation){};
}
