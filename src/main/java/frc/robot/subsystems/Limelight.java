// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class Limelight extends SubsystemBase {
//   private double tv, tx, ty, ts;
//   public double m_LimeLightSteerCommand = tx * Constants.STEER_K;
//   /**
//    * Creates a new Limelight.
//    */
//   public Limelight() {



//   }

//   public enum LEDState {
//     on, off, blink
//   }

//   public boolean isValidTarget(){
//     tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
//     return tv == 1;
//   }

//   public double getHorizontalAngle(){
//     tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
//     return tx;
//   }

//   public double getVerticalAngle(){
//     ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
//     return ty;
//   }

//   public void setLEDState(String ledState) {
//     switch (ledState) {
//      case "on" :
//         NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
//         break;
//       case "off" :         
//         NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
//         break;
//       case "blink" :
//         NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(2);
//         break;
//      default:
//         NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
//     }
//   }

//   public double getDistance(double targetHeight, double targetAngle){
//     //Target height can be a constant and target angle should be replaced by ty (?)
//     return (Constants.POWERPPORT_HEIGHT-Constants.LIMELIGHT_MOUNTING_HEIGHT) / Math.tan(Constants.LIMELIGHT_MOUNTING_ANGLE+ty);
//   }

//   public double getAngle() {
//     ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
//     return ts;
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
