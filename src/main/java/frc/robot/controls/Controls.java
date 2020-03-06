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
    *       back        : 7
    *       start       : 8
    *       left stick  : 9
    *       right stick : 10   
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
    // TODO Leave this empty for now (Sarah and I will fill this out on our own)
        public static final int INTAKE_UP = 5;
        public static final int INTAKE_DOWN = 0;
        public static final int INTAKE_INTAKE = 3;
        public static final int SHOOT = 6;
        public static final int LOAD_BALL = 3;
        public static final int MAGAZINE = 1;
        public static final int SLIDE_UP = 0;
        public static final int WINCH = 0;
        public static final int CONTPANE_UP = 5;
        public static final int CONTPANE_SPIN = 7;
        public static final int MAG_THING = 9;
}