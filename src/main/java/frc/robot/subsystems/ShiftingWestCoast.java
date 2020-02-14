
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
// import frc.robot.Utils;


public class ShiftingWestCoast extends Subsystem {

    // Declare drive train parts
    CANSparkMax rightMaster, rightSlave, leftMaster, leftSlave;
    DifferentialDrive drive;

    
    private CANEncoder leftEncoder, rightEncoder;

    private static AHRS navx;

    DoubleSolenoid shifter;

    


    public ShiftingWestCoast() {
        initDrive();
        initShift();
        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();
    }

    public void initDrive() {

        // Initialize motor DSs
        rightMaster = new CANSparkMax(RobotMap.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
        rightSlave = new CANSparkMax(RobotMap.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

        leftMaster = new CANSparkMax(RobotMap.DRIVE_LEFT_MASTER, MotorType.kBrushless);
        leftSlave = new CANSparkMax(RobotMap.DRIVE_LEFT_SLAVE, MotorType.kBrushless);

        // Encoder setup

        rightEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4096);
        leftEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4096);


        resetMotors();


        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setIdleMode(IdleMode.kBrake);
        leftMaster.setIdleMode(IdleMode.kBrake);

        drive = new DifferentialDrive(rightMaster, leftMaster);
        drive.setMaxOutput(Constants.DRIVE_LOW); // Maybe change this to high if its too slow

    }

    

    public void drive(DriveMode mode, double speedInput, double turnInput, int sensitivityLevel) {
        double speed = speedInput * Constants.DRIVE_HIGH;
        double rotation = turnInput * Constants.TURN_SPEED;
        switch(mode) {
          //helllllllllllllllllllllllllllllllllllllllllo
          //Hi
        case kArcade:
            drive.arcadeDrive(speed, rotation);
            break;
        case kCurve:
            drive.curvatureDrive(speed, rotation, true);
            break;
            //how are yoiu
            //good :)
        }
    }

    public enum DriveMode {
        kArcade, kCurve;
    }
    
    public void resetMotors() {
        rightMaster.restoreFactoryDefaults();
        leftMaster.restoreFactoryDefaults();
    }

    public void setCoast() {
        leftMaster.setIdleMode(IdleMode.kCoast);
        rightMaster.setIdleMode(IdleMode.kCoast);
    
      }

    public void setBrake() {
        rightMaster.setIdleMode(IdleMode.kBrake);
        leftMaster.setIdleMode(IdleMode.kBrake);
      //no no no no no no no no no 
      // nonononononon
      }
      private void initShift() {
          //init soloenid
        shifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_A, RobotMap.DRIVE_SHIFT_B);
        shifter.set(Value.kForward); 
      }

      public void shift(boolean highGear) {
        if (highGear) {
          shifter.set(Value.kForward);
        } else {
          shifter.set(Value.kReverse);
        }
      }

      public void restGyro(){
        navx.reset();
      }

      public void getHeading() {
        navx.getAngle();
      }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}