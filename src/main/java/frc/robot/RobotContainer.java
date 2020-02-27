/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeExtend;
import frc.robot.commands.MagazineAdvance;
import frc.robot.controls.Controls;
import frc.robot.controls.DS;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.ShiftingWestCoast;
import frc.robot.subsystems.ShiftingWestCoast.DriveMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.*;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private ShiftingWestCoast drive = new ShiftingWestCoast();
  private Intake intake = new Intake();
  private Magazine magazine = new Magazine();
  private ControlPanel controlPanel = new ControlPanel();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    new RunCommand(() -> drive.drive(DriveMode.kCurve, 
        DS.getGTASpeed(), 
        DS.getTurn(), 
        Controls.SENSITIVITY, 
        DS.getLowGear(), 
        DS.getHighGear()),
      drive);

    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(DS.driver, Controls.INTAKE_SLIDE)
    .toggleWhenPressed(new IntakeExtend(intake));

    new JoystickButton(DS.driver, Controls.INTAKE_INTAKE)
    .whenHeld(new IntakeExtend(intake));
  

  new JoystickButton(DS.operator, Controls.MAGAZINE)
    .whenHeld(new MagazineAdvance(magazine));

    new JoystickButton(DS.operator, Controls.CONTPANE_UP)
    .toggleWhenPressed(new IntakeExtend(intake));

    new JoystickButton(DS.operator, Controls.MAGAZINE)
    .whenHeld(new MagazineAdvance(magazine));
  

}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
