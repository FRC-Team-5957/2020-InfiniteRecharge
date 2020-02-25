// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import frc.robot.RobotMap;

// public class Shooter extends SubsystemBase {


//   private CANSparkMax shooter;
//   private CANEncoder encoder;
//   /**
//    * Creates a new Shooter.
//    */
//   public Shooter() {
//     initMotor();
//   }

//   private void initMotor() {
//     shooter = new CANSparkMax(RobotMap.SHOOTER_ID, MotorType.kBrushless);
//     shooter.setIdleMode(IdleMode.kBrake);
//   }

//   public void shoot() {
//     shooter.set(Constants.SHOOT_SPEED);
//   }

//   public void idle() {
//     shooter.set(Constants.IDLE_SPEED);
//   }

//   public double getVelocity() {
//     return encoder.getVelocity();
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
