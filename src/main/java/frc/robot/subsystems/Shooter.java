/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {


  private WPI_TalonSRX shooter;
  private Encoder encoder;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    initMotor();
  }

  private void initMotor() {
    shooter = new WPI_TalonSRX(RobotMap.SHOOTER_ID);
  }
  private void initEcoder(){
      encoder = new Encoder(2, 3);
      encoder.setDistancePerPulse(2*Math.PI/Constants.FLY_WHEEL_ENCODER_COUNT);
  }

  public void shoot() {
    shooter.set(ControlMode.PercentOutput, Constants.SHOOT_SPEED);
  }

  public void shoot(double velocity) {
    shooter.set(ControlMode.Velocity, velocity/Constants.FLY_WHEEL_RADIUS);
  }

  public void lowShoot() {
    shooter.set(ControlMode.Velocity, Constants.LOW_SHOOT_SPEED);
  }

  public void idle() {
    shooter.set(ControlMode.PercentOutput, Constants.IDLE_SPEED);
  }

  public double getSpeed(){
    return encoder.getRate();
    // return 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
