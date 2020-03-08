
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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class ShiftingWestCoast extends SubsystemBase  {
    // Declare drive train parts
    CANSparkMax rightMaster, rightSlave, leftMaster, leftSlave;
    DifferentialDrive drive;
    Timer timer;
    
    private CANEncoder leftEncoder, rightEncoder;

    private static AHRS navx;

    DoubleSolenoid shifter;

     // Odometry class for tracking robot pose
    private DifferentialDriveOdometry m_odometry;

    //153,600/6,480 


    public ShiftingWestCoast() {
        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();
        initDrive();
        initShift();
        timer = new Timer();

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

        rightEncoder = new CANEncoder(rightMaster, EncoderType.kHallSensor, Constants.countsPerRev);
        leftEncoder = new CANEncoder(leftMaster, EncoderType.kHallSensor, Constants.countsPerRev);


        
        rightEncoder.setPositionConversionFactor(Constants.countsPerMeter);
        leftEncoder.setPositionConversionFactor(Constants.countsPerMeter);
        


        resetMotors();


        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setIdleMode(IdleMode.kBrake);
        leftMaster.setIdleMode(IdleMode.kBrake);

        drive = new DifferentialDrive(rightMaster, leftMaster);
        drive.setMaxOutput(Constants.DRIVE_LOW); // Maybe change this to high if its too slow
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
        drive.setSafetyEnabled(false);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
      return new DifferentialDriveWheelSpeeds(rightEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    public double getLSpeed(){
      return leftEncoder.getVelocity();
    }

    public double getRSpeed(){
      return rightEncoder.getVelocity();
    }


    

    public void drive(DriveMode mode, double speedInput, double turnInput, int sensitivityLevel) {
        double speed = speedInput * Constants.DRIVE_HIGH;
        double rotation = turnInput * Constants.TURN_SPEED;
        switch(mode) {
          //helllllllllllllllllllllllllllllllllllllllllo
          //Hi
          //hey :)
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
      public float getHeading() {
       return navx.getYaw();
      }

        /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }
   /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMaster.setVoltage(leftVolts);
    rightMaster.setVoltage(-rightVolts);
    drive.feed();
    // "It is very important to use the setVoltage() method rather than the ordinary set() method,
    //  as this will automatically compensate for battery “voltage sag” during operation. 
    //  Since our feedforward voltages are physically-meaningful (as they are based on measured characterization data), 
    //  this is essential to ensuring their accuracy."
  }

  public void driveTo(double position){
    double speed = 0.7;
    double leftTarget = leftEncoder.getPosition() + position;
    double rightTarget = rightEncoder.getPosition() + position;
    if (position>0){
    while (leftEncoder.getPosition() < leftTarget && rightEncoder.getPosition() < rightTarget) {
      leftMaster.set(speed);
      rightMaster.set(-speed);
    }
  } else {
    while (leftEncoder.getPosition() > leftTarget && rightEncoder.getPosition() > rightTarget) {
      rightMaster.set(speed);
      leftMaster.set(-speed);
  }
    rightMaster.set(0);
    leftMaster.set(0);

  }
}

public void offLine(){
  double speed = 0.3;
    timer.reset();
    timer.start();
    while(timer.get() <= 2){
      rightMaster.set(-speed);
      leftMaster.set(speed);
    }
    rightMaster.set(0);
    leftMaster.set(0);
}
}