package frc.robot.Teleop;

// Custom file imports
import frc.robot.Constants;
import frc.robot.controls.Controls;
import frc.robot.controls.DS;
import frc.robot.subsystems.ShiftingWestCoast;
import frc.robot.subsystems.ShiftingWestCoast.DriveMode;
// import frc.robot.subsystems.Limelight;


/**
 * For driving in tele-op no yes
 */
public class Drive {
   ShiftingWestCoast drive;
//    Limelight ll;

    public Drive(){
        drive = new ShiftingWestCoast();
        // ll = new Limelight();
    }

    public void drive() {

        DS.getGTASpeed();
       
        double speedInput = DS.getLowGear() ? DS.getGTASpeed() * Constants.DRIVE_LOW : DS.getGTASpeed();
        double turnInput = DS.getTurn();
        boolean highGear = DS.getHighGear();
    
        if (DS.getLimelightStraigten()) {
            // drive.drive(DriveMode.kCurve, speedInput, ll.m_LimeLightSteerCommand, Controls.SENSITIVITY);
        } else {
            drive.drive(DriveMode.kCurve, speedInput, turnInput, Controls.SENSITIVITY);
        }
        drive.shift(highGear);


    
      }
}