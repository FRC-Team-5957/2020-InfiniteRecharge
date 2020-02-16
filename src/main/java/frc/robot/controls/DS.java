package frc.robot.controls;

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

    //Operator controls
    public static boolean getShoot() {
        return operator.getRawButton(Controls.SHOOT);
    }

    // Utils
    private static double getAxis(int axis, Joystick j) {
        // only return axis values above 0.05
        return Math.abs(j.getRawAxis(axis)) < 0.05 ? 0 : j.getRawAxis(axis);
    }

    
}