// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Swervedrive.Drivetrain;
import frc.robot.Subsystems.Swervedrive.GyroIO;
import frc.robot.Subsystems.Swervedrive.ModuleIO;

public class RobotContainer {
  CommandXboxController controller = new CommandXboxController(0);
  Drivetrain swerveDrive;
  ModuleIO frontLeft, frontRight, backLeft, backRight;
  Translation2d frontLeftOffset, frontRightOffset, backLeftOffset, backRightOffset;
  GyroIO gyro;

  public RobotContainer() {
    this.swerveDrive = new Drivetrain(frontLeft, frontLeftOffset, frontRight, frontRightOffset, backLeft, backLeftOffset, backRight, backRightOffset, gyro);
    configureBindings();
  }

  private void configureBindings() {
    DoubleSupplier vx = () -> controller.getLeftX();
    DoubleSupplier vy = () -> controller.getLeftY();
    DoubleSupplier vTheta = () -> controller.getRightX();
    this.swerveDrive.setDefaultCommand(
      this.swerveDrive.swerveDriveCommand(vx, vy, vTheta)
    );

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
