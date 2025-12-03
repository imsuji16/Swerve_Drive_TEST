package frc.robot.Subsystems.Swervedrive;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

public class ModuleIOTalonFX implements ModuleIO {

    TalonFX drive, turn;
    double offset = 0;
    double driveMetersPerRotation = Constants.driveMetersPerRotation;
    double steerRadiansPerRotation = Constants.steerRadiansPerRotation;

    public ModuleIOTalonFX(int driveID, int turnID, int absoluteEncoderID, double driveKFF, double driveKP, double driveKI, double driveKD, double turnKP, double turnKI, double turnKD, double driveMetersPerRotation, double steerRadiansPerRotation, double offset) {
        this.drive = new TalonFX(driveID);
        this.turn = new TalonFX(turnID);
        this.offset = offset;
        this.driveMetersPerRotation = driveMetersPerRotation;
        this.steerRadiansPerRotation = steerRadiansPerRotation;
        
        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        driveConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        driveConfig.Slot0.kV = driveKFF;
        driveConfig.Slot0.kP = driveKP;
        driveConfig.Slot0.kI = driveKI;
        driveConfig.Slot0.kD = driveKD;
        this.drive.getConfigurator().apply(driveConfig);

        TalonFXConfiguration turnConfig = new TalonFXConfiguration();
        turnConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        turnConfig.Slot0.kP = turnKP;
        turnConfig.Slot0.kI = turnKI;
        turnConfig.Slot0.kD = turnKD;
        this.turn.getConfigurator().apply(turnConfig);
    }

    @Override
    public double getSpeed() {
        return this.drive.getVelocity().getValue().in(RadiansPerSecond) * Units.inchesToMeters(Constants.WHEEL_RADIUS_IN_INCHES);
    }

    @Override
    public double getOrientation() {
        return (this.turn.getPosition().getValueAsDouble() - this.offset) * this.steerRadiansPerRotation;
    }

    @Override
    public void setSpeed(double speed) {
        this.drive.setControl(new VelocityVoltage(speed / this.driveMetersPerRotation));
    }

    @Override
    public void setOrientation(double orientation) {
        this.turn.setControl(new PositionVoltage((orientation + this.offset) / this.steerRadiansPerRotation));
    }

    @Override
    public void setOrientationOffset(double offset) {
        this.offset = offset;
    }
   
}