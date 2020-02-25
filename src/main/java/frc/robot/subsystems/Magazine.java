// package frc.robot.subsystems;

// import frc.robot.Constants;
// import frc.robot.RobotMap;

// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import com.ctre.phoenix.motorcontrol.ControlMode;

// public class Magazine extends SubsystemBase {
//     TalonSRX magBot, magTop;

//     public Magazine() {
//         initMotors();
//     }

//     public void initMotors() {
//         magBot = new TalonSRX(RobotMap.MAGBOT_BELT);
//         magTop = new TalonSRX(RobotMap.MAGTOP_BELT);
//     }

//     public void advance() {
//         magBot.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
//         magTop.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
//     }
//     //Add in pneumatic pistons 
// }