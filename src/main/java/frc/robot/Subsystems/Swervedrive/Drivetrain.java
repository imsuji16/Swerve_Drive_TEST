package frc.robot.Subsystems.Swervedrive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {

    ModuleIO frontLeft, frontRight, backLeft, backRight;
    GyroIO gyro;
    SwerveDriveKinematics kinematics;
    ModuleIO[] modules;

    public Drivetrain(ModuleIO frontLeft, Translation2d frontLeftOffset, ModuleIO frontRight, Translation2d frontRightOffset, ModuleIO backLeft, Translation2d backLeftOffset, ModuleIO backRight, Translation2d backRightOffset, GyroIO gyro) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.gyro = gyro;
        this.kinematics = new SwerveDriveKinematics(frontLeftOffset, frontRightOffset, backLeftOffset, backRightOffset);
        this.modules = new ModuleIO[] {this.frontLeft, this.frontRight, this.backLeft, this.backRight};

        this.frontLeft = new ModuleIOTalonFX(RobotMap.FRONT_LEFT_DRIVE_ID, RobotMap.FRONT_LEFT_TURN_ID, RobotMap.FRONT_LEFT_ENCODER_ID, Constants.DRIVE_KFF, Constants.DRIVE_KP, Constants.DRIVE_KI, Constants.DRIVE_KD, Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD, Constants.WHEEL_RADIUS_IN_INCHES * 2 * Math.PI, Math.PI, Constants.FRONT_LEFT_OFFSET);
        this.frontRight = new ModuleIOTalonFX(RobotMap.FRONT_RIGHT_DRIVE_ID, RobotMap.FRONT_RIGHT_TURN_ID, RobotMap.FRONT_RIGHT_ENCODER_ID, Constants.DRIVE_KFF, Constants.DRIVE_KP, Constants.DRIVE_KI, Constants.DRIVE_KD, Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD, Constants.WHEEL_RADIUS_IN_INCHES * 2 * Math.PI, Math.PI, Constants.FRONT_RIGHT_OFFSET);
        this.backLeft = new ModuleIOTalonFX(RobotMap.BACK_LEFT_DRIVE_ID, RobotMap.BACK_LEFT_TURN_ID, RobotMap.BACK_LEFT_ENCODER_ID, Constants.DRIVE_KFF, Constants.DRIVE_KP, Constants.DRIVE_KI, Constants.DRIVE_KD, Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD, Constants.WHEEL_RADIUS_IN_INCHES * 2 * Math.PI, Math.PI, Constants.BACK_LEFT_OFFSET);
        this.backRight = new ModuleIOTalonFX(RobotMap.BACK_RIGHT_DRIVE_ID, RobotMap.BACK_RIGHT_TURN_ID, RobotMap.BACK_RIGHT_ENCODER_ID, Constants.DRIVE_KFF, Constants.DRIVE_KP, Constants.DRIVE_KI, Constants.DRIVE_KD, Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD, Constants.WHEEL_RADIUS_IN_INCHES * 2 * Math.PI, Math.PI, Constants.BACK_RIGHT_OFFSET);
    }

    public void setSwerveModuleStates(SwerveModuleState[] states) {
        for (int i = 0; i < states.length; i++) {
            modules[i].setSpeed(states[i].speedMetersPerSecond);
            modules[i].setOrientation(states[i].angle.getRadians());
        }
    }

    public Command swerveDriveCommand(DoubleSupplier vx, DoubleSupplier vy, DoubleSupplier vTheta) {
        ChassisSpeeds robotRelativeSpeeds = new ChassisSpeeds(vx.getAsDouble(), vy.getAsDouble(), vTheta.getAsDouble());
        ChassisSpeeds fieldRelativeSpeeds = ChassisSpeeds.fromRobotRelativeSpeeds(robotRelativeSpeeds, this.gyro.getOrientation());
        SwerveModuleState[] states = this.kinematics.toSwerveModuleStates(fieldRelativeSpeeds);
        return this.run(() -> this.setSwerveModuleStates(states));
    }

    @Override
    public void periodic() {}
}
