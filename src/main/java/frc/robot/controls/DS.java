package frc.robot.controls;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

// Custom file imports
import frc.robot.RobotMap;

public class DS {
    // Declare joysticks
    public static Joystick driver, operator;
    
    public DS() {
        //Initialize joysticks
        driver = new Joystick(RobotMap.DRIVER_USB);
        operator = new Joystick(RobotMap.OPERATOR_USB);
    }

    // Drive train controls
    public static double getGTASpeed() {
        return getAxis(Controls.GTA_DECCEL, driver) - getAxis(Controls.GTA_ACCEL, driver);
    }

    public static double getTurn() {
        return -driver.getRawAxis(Controls.DRIVE_TURN_AXIS);
    }

    public static boolean getHighGear() {
        return driver.getRawButton(Controls.DRIVE_HIGHGEAR);
    }

    public static boolean getLowGear() {
        return driver.getRawButton(Controls.DRIVE_SLOW);
    }
    
    public static boolean getLimelightStraigten() {
        return driver.getRawButton(Controls.LIMELIGHT_BUTTON);
    }

    //Operator controls
    public static boolean getShoot() {
        return operator.getRawButton(Controls.SHOOT);
    }

    public boolean getIntExtend() {
        return operator.getRawButtonPressed(Controls.INTAKE_DOWN);
    }

    public boolean getIntRetract() {
        return operator.getRawButtonPressed(Controls.INTAKE_UP);
    }

    public static boolean getIntDown() {
     if (operator.getPOV() == 0) {
         return true;
     } else {
         return false;
     }
    }

    public static boolean getIntUp() {
        if (operator.getPOV() == 180) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean getContPaneSpin() {
        if (operator.getPOV() == 270) {
            return true;
        } else {
            return false;
        }
    }

    public static double getIntSpin() {
        return getAxis(Controls.INTAKE_INTAKE, operator);
    }

    public static boolean getMagazine() {
        return operator.getRawButton(Controls.MAGAZINE);
    }

    public static boolean getControlPanelExtend() {
        return operator.getRawButtonPressed(Controls.CONTPANE_UP);
    }

    public static boolean getControlPanelSpin() {
        return operator.getRawButton(Controls.CONTPANE_SPIN);
    }

    public static boolean getMagThing() {
        return operator.getRawButton(Controls.MAG_THING);
    }

    // Utils
    private static double getAxis(int axis, Joystick j) {
        // only return axis values above 0.05
        return Math.abs(j.getRawAxis(axis)) < 0.05 ? 0 : j.getRawAxis(axis);
    }

    public static void vibrate(Joystick j, double intensity) {
        j.setRumble(RumbleType.kLeftRumble, intensity);
        j.setRumble(RumbleType.kRightRumble, intensity);
    }
}