/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import java.io.IOException;

import org.json.simple.parser.ParseException;

// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

// import frc.robot.subsystems.ShiftingWestCoast;
// import frc.robot.subsystems.ShiftingWestCoast.DriveMode;
import frc.robot.controls.DS;
import frc.robot.Paths.AutoPaths;
import frc.robot.Teleop.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.ControlPanel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // private static final String kDefaultAuto = "Default";
  // private static final String kCustomAuto = "My Auto";
  // private String m_autoSelected;
  // private final SendableChooser<String> m_chooser = new SendableChooser<>();

  DS DS;
  public Drive drive;
  Intake intake;
  Magazine mag;
  ControlPanel contPanel;

  AutoPaths auto;

  // private double m_LimelightSteerCommand = 0.0;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);

    DS = new DS();
    drive = new Drive();
    intake = new Intake();
    contPanel = new ControlPanel();
    try {
      auto = new AutoPaths();
    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
   
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    auto.autoMove("PathWeaver\\output\\Forward.wpilib.json");
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

  public void win() {
    drive.drive();
    intakeControl();
    magazineControl();
    controlPanelControl();
  }

  public void intakeControl() {
    boolean extended = false;
    if (DS.getIntExtend()) {
      extended = !extended;
    }

    if (DS.getIntSpin()) {
      intake.intake();
    }

    intake.extend(extended);
  }

  public void magazineControl() {
    if (DS.getMagazine()) {
      mag.advance();
    }
    if (DS.getMagThing()) {
      mag.something(true);
    } else {
      mag.something(false);
    }
  }

  public void controlPanelControl() {
    boolean extended = false;
    if (DS.getControlPanelExtend()) {
      extended = !extended;
    }

    if (DS.getControlPanelSpin()) {
      contPanel.panelSpin();
    }

    contPanel.extend(extended);
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


}
