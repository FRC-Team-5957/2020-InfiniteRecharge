
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
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
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

     // Odometry class for tracking robot pose
    private DifferentialDriveOdometry m_odometry;

    


    public ShiftingWestCoast() {
        initDrive();
        initShift();
        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();
    }

    @Override
    public void periodic() {
      // Update the odometry in the periodic block
      m_odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(),
      rightEncoder.getPosition());
    }

    public void initDrive() {

        // Initialize motor DSs
        rightMaster = new CANSparkMax(RobotMap.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
        rightSlave = new CANSparkMax(RobotMap.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

        leftMaster = new CANSparkMax(RobotMap.DRIVE_LEFT_MASTER, MotorType.kBrushless);
        leftSlave = new CANSparkMax(RobotMap.DRIVE_LEFT_SLAVE, MotorType.kBrushless);

        // Encoder setup

        rightEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, Constants.countsPerRev);
        leftEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, Constants.countsPerRev);

        
        rightEncoder.setPositionConversionFactor(Constants.countsPerMeter);
        leftEncoder.setPositionConversionFactor(Constants.countsPerMeter);
        


        resetMotors();


        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setIdleMode(IdleMode.kBrake);
        leftMaster.setIdleMode(IdleMode.kBrake);

        drive = new DifferentialDrive(rightMaster, leftMaster);
        drive.setMaxOutput(Constants.DRIVE_LOW); // Maybe change this to high if its too slow

    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
      return new DifferentialDriveWheelSpeeds(rightEncoder.getVelocity(), rightEncoder.getVelocity());
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
      //ye seyes eyes yes yes 
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


  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
      public double getHeading() {
       return navx.getYaw();
      }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}