/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.io.IOException;

import org.json.simple.parser.ParseException;

// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.controls.Controls;
// import frc.robot.subsystems.ShiftingWestCoast;
// import frc.robot.subsystems.ShiftingWestCoast.DriveMode;
import frc.robot.controls.DS;
import frc.robot.Paths.AutoPaths;
// import frc.robot.Teleop.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.ShiftingWestCoast;
// import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight.LEDState;
import frc.robot.subsystems.ShiftingWestCoast.DriveMode;
import frc.robot.subsystems.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Command autonomousCommand;
  // private static final String kDefaultAuto = "Default";
  // private static final String kCustomAuto = "My Auto";
  // private String m_autoSelected;
  // private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public ShiftingWestCoast drive;
  Intake intake;
  Magazine mag;
  Shooter shooter;
  Limelight ll;

  boolean stop;
  // ControlPanel contPanel;

  AutoPaths auto;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);

    DS ds = new DS();

    drive = new ShiftingWestCoast();
    shooter = new Shooter();
    intake = new Intake();
    mag = new Magazine();
    ll = new Limelight();
    stop = false;
    // contPanel = new ControlPanel();
    // try {
    //   auto = new AutoPaths(drive);
    // } catch (IOException | ParseException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // CommandScheduler.getInstance().run();
    // System.out.printf("Shooter Speed: %.4f", shooter.getSpeed());
    // System.out.printf("Drive Speed:   %.4f", drive.getWheelSpeeds());
    // System.out.printf("Left Speed:    %.4f   Right Speed:     %.4f", drive.getLSpeed(), drive.getRSpeed());
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    drive.offLine();
    // lineTaget();

    // Timer.delay(1);

    // shooter.shoot();
    // mag.shoot();
    // Timer.delay(0.5);

    // mag.advance();
    // Timer.delay(1);

    // mag.stopoAdvance();
    // mag.shoot();
    // Timer.delay(1);

    // mag.advance();
    // Timer.delay(0.5);
    // mag.stopoAdvance();
    // mag.shoot();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // drive.driveTo(0.05);
    // Timer.delay(1);
    // lineTaget();

    // Timer.delay(1);

    // shooter.shoot();
    // mag.shoot();
    // Timer.delay(0.5);

    // mag.advance();
    // Timer.delay(1);

    // mag.stopoAdvance();
    // mag.shoot();
    // Timer.delay(1);

    // mag.advance();
    // Timer.delay(0.5);
    // mag.stopoAdvance();
    // mag.shoot();
    
    
    // auto.autoMove("PathWeaver/output/Forward.wpilib.json");
    // autonomousCommand = new 
   //helo
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    win(); // Actually just teleopControl()
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


  @Override
  public void teleopInit(){

  }

  @Override
  public void disabledInit(){

  }

  @Override
  public void disabledPeriodic() {
    
  }

  public void win() {
    drive();
    intakeControl();
    magazineControl();
    controlPanelControl();
    shooterControl();
  }

  public void drive() {

    DS.getGTASpeed();
   
    double speedInput = DS.getLowGear() ? DS.getGTASpeed() * Constants.DRIVE_LOW : DS.getGTASpeed();
    double turnInput = DS.getTurn();
    boolean highGear = DS.getHighGear();

    if (DS.getLimelightStraigten()) {
        drive.drive(DriveMode.kCurve, speedInput, ll.getLLSteering(), Controls.SENSITIVITY);
        ll.setLEDState(LEDState.on);
        if (-0.05 < ll.getLLSteering() && ll.getLLSteering() < 0.05) {
          DS.vibrate(DS.driver, 0.4);
        }
    } else {
        drive.drive(DriveMode.kCurve, speedInput, turnInput, Controls.SENSITIVITY);
        ll.setLEDState(LEDState.off);
    }

    drive.shift(!highGear);



  }

  public void shooterControl(){
    if(DS.getShoot()){
      shooter.shoot();
    } if (DS.getLowShot()) {
      shooter.lowShoot();
    } else{
      shooter.idle();
    }
  }

  public void intakeControl() {
    // boolean extended = false;
    // if (DS.getIntExtend()) {
    //   extended = !extended;
    // }

    if (DS.getIntSpin() != 0) {
      intake.intake();
    } else {
      intake.stopoIntake();
    }

    if (DS.getIntDown()) {
      intake.extend();
    }
    if (DS.getIntUp()) {
      intake.retract();
    }

    // intake.extend(extended);
  }

  public void magazineControl() {
   

    if (DS.getMagThing()) {
      mag.extend();
      stop = true;
    } else {
      mag.retract(); 
      stop = false;
    }

    if (DS.getMagazine() && !stop) {
      mag.advance();
    }if (DS.getMagUnjam()) {
      mag.unjam();
    } else {
      mag.stopoAdvance();
    }
  }

  public void controlPanelControl() {
    // boolean extended = false;
    // if (DS.getControlPanelExtend()) {
    //   extended = !extended;
    // }

    // if (DS.getControlPanelSpin()) {
    //   contPanel.panelSpin();
    // }

    // contPanel.extend(extended);
  }


  public void positionControl() {
    //For moving the wheel to the right color
    //TODO  move this to a file just for the wheel control, too much stuff in this folder
    //https://docs.wpilib.org/en/latest/docs/software/wpilib-overview/2020-Game-Data.html
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
{
  switch (gameData.charAt(0))
  {
    case 'B' :
      //Blue case code
      //hi how are you 
      break;
    case 'G' :
      //Green case code
      break;
    case 'R' :
      //Red case code
      break;
    case 'Y' :
      //Yellow case code
      break;
      //get that code doen girl
    default :
      //This is corrupt data
      break;
      //smile every day
  }
} else {
  //Code for no data received yet
}
  }

  public void lineTaget(){
    int threshhold = 1;
    while (-threshhold > ll.getHorizontalAngle() || ll.getHorizontalAngle() > threshhold){
      ll.setLEDState(LEDState.on);
      drive.drive(DriveMode.kArcade, 0, ll.getLLSteering(), Controls.SENSITIVITY);
    }
    drive.drive(DriveMode.kArcade, 0, 0, Controls.SENSITIVITY);
    ll.setLEDState(LEDState.off);
  }


}
