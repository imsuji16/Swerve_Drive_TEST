package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

public class Constants {
    public static final double DRIVE_KFF = 0.124;
    public static final double DRIVE_KP = 1.2;
    public static final double DRIVE_KI = 0.0;
    public static final double DRIVE_KD = 0.0;

    public static final double TURN_KP = 100.0;
    public static final double TURN_KI = 0.0;
    public static final double TURN_KD = 0.5;

    public static final double FRONT_LEFT_ZERO = 0.089111328125;
    public static final double FRONT_RIGHT_ZERO = 0.112548828125;
    public static final double BACK_LEFT_ZERO = -0.18212890625;
    public static final double BACK_RIGHT_ZERO = 0.147216796875;

    public static final double DRIVE_GEAR_RATIO = 5.90277777777778;
    public static final double TURN_GEAR_RATIO = 21.428571428571427;

    public static final Translation2d FRONT_LEFT_OFFSET = new Translation2d(11.5, 11.5);
    public static final Translation2d FRONT_RIGHT_OFFSET = new Translation2d(11.5, -11.5);
    public static final Translation2d BACK_LEFT_OFFSET = new Translation2d(-11.5, 11.5);
    public static final Translation2d BACK_RIGHT_OFFSET = new Translation2d(-11.5, -11.5);
 
    public static final double WHEEL_RADIUS_IN_INCHES = 2.0;

    public static final double driveMetersPerRotation = 2 * Math.PI * WHEEL_RADIUS_IN_INCHES / DRIVE_GEAR_RATIO; // replace with actual values
    public static final double steerRadiansPerRotation = 2 * Math.PI / TURN_GEAR_RATIO; // replace with actual values
}
