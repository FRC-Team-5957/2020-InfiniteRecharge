// package frc.robot.subsystems;

// import frc.robot.Constants;
// import frc.robot.RobotMap;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Magazine extends SubsystemBase {
//     CANSparkMax magBot, magTop;

//     //I just didnt know what to call this but you dont have to give it a name
//     DoubleSolenoid somethingIdk;

//     public Magazine() {
//         initMotors();
//         initSolenoid();
//     }

//     public void initMotors() {
//         magBot = new CANSparkMax(RobotMap.MAGBOT_BELT, MotorType.kBrushless);
//         magTop = new CANSparkMax(RobotMap.MAGTOP_BELT, MotorType.kBrushless);
//     }

//     public void initSolenoid() {
//         somethingIdk = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.MAG_SOMETHING_A, RobotMap.MAG_SOMETHING_B);
//     }

//     public void advance() {
//         magBot.set(Constants.INTAKE_SPEED);
//         magTop.set(Constants.INTAKE_SPEED);
//     }

//     public void something(boolean on) {
//         if (on == true) {
//             somethingIdk.set(Value.kForward);
//         } else if (on == false) {
//             somethingIdk.set(Value.kReverse);
//         }
//     }
//     //Add in pneumatic pistons 
// }