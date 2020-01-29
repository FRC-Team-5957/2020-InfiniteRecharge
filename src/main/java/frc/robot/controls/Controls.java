package frc.robot.controls;

public class Controls {

    /* Contoller mappings:
    *   Buttons :
    *       (A) button : 1
    *       (B) button : 2
    *       (X) button : 3
    *       (Y) button : 4
    *       left bumper : 5 
    *       right bumper : 6
    *   Axis :
    *       Left stick :
    *           X axis : 0 
    *           Y axis : 1 
    *       Right stick :
    *           X axis : 4 
    *           Y axis : 5 
    *       Left trigger : 2 
    *       Right trigger : 3 
    */

    // Controls for the driver and operator

    // Driver controls
        public static final int DRIVE_SPEED_AXIS = 1;
        public static final int DRIVE_TURN_AXIS = 0;
        public static final int DRIVE_HIGHGEAR = 1; 
        public static final int DRIVE_SLOW = 2; 
        public static final int GTA_ACCEL = 3; 
        public static final int GTA_DECCEL = 2; 
        public static final int HEADING_BUTTON = 3; 
        public static final int LIMELIGHT_BUTTON = 4; 

        public static final int SENSITIVITY = 3;

    // Operator controls
    // Leave this empty for now (Sarah and I will fill this out on our own)
}