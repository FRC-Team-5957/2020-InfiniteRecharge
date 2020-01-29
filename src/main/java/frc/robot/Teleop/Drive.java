package frc.robot.Teleop;

// Custom file imports
import frc.robot.Constants;
import frc.robot.controls.Controls;
import frc.robot.controls.DS;
import frc.robot.subsystems.ShiftingWestCoast;
import frc.robot.subsystems.ShiftingWestCoast.DriveMode;


/**
 * For driving in tele-op no
 */
public class Drive {
   ShiftingWestCoast drive;

    public Drive(){
        drive = new ShiftingWestCoast();
    }

    public void drive() {

        DS.getGTASpeed();
       
        double speedInput = DS.getLowGear() ? DS.getGTASpeed() * Constants.DRIVE_LOW : DS.getGTASpeed();
        double turnInput = DS.getTurn();
        boolean highGear = DS.getHighGear();
    
        drive.drive(DriveMode.kCurve, speedInput, turnInput, Controls.SENSITIVITY);
        drive.shift(highGear);


    
      }
}