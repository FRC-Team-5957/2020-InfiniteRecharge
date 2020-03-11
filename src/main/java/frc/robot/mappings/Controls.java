package frc.robot.mappings;

public class Controls {

    // Contoller mappings:
    //   Buttons :
    //      (A) button : 1
    public static final int A_BUTTON = 1;
    //       (B) button : 2
    public static final int B_BUTTON = 2;
    //       (X) button : 3
    public static final int X_BUTTON = 3;
    //       (Y) button : 4
    public static final int Y_BUTTON = 4;
    //      left bumper : 5 
    public static final int LEFT_BUMPER = 5;
    //      right bumper : 6
    public static final int RIGHT_BUMPER = 6;
    //      back        : 7 (?)
    //     right stick : 10   
    //   Axis :
    //      Left stick :
    //          X axis : 0 
    public static final int XAXIS_LEFT = 0;
    //          Y axis : 1 
    public static final int YAXIS_LEFT = 1;
    //      Right stick :
    //          X axis : 4 
    public static final int XAXIS_RIGHT = 4;
    //          Y axis : 5 
    public static final int YAXIS_RIGHT = 5;
    //      Left trigger : 2 
    public static final int LEFT_TRIGGER = 2;
    //      Right trigger : 3 
    public static final int RIGHT_TRIGGER = 3;
    //

    // Controls for the driver and operator

    // Driver controls
        public static final int DRIVE_SPEED_AXIS = 1;//unused
        public static final int DRIVE_TURN_AXIS = 0;//unused
        public static final int DRIVE_HIGHGEAR = A_BUTTON; 
        public static final int DRIVE_SLOW = B_BUTTON; 
        public static final int GTA_ACCEL = RIGHT_TRIGGER; 
        public static final int GTA_DECCEL = LEFT_TRIGGER; 
        public static final int HEADING_BUTTON = X_BUTTON; 
        public static final int LIMELIGHT_BUTTON = Y_BUTTON; 

        public static final int SENSITIVITY = 3;

    // Operator
    // TODO Leave this empty for now (Sarah and I will fill this out on our own)
        public static final int INTAKE_UP = 5; // DPAD down
        public static final int INTAKE_DOWN = 0; //DPAD up
        public static final int INTAKE_INTAKE = LEFT_BUMPER;
        public static final int SHOOT = RIGHT_BUMPER;
        public static final int LOWGOAL_SHOOT = B_BUTTON;
        public static final int LOAD_BALL = RIGHT_TRIGGER;
        public static final int MAGAZINE = A_BUTTON;
        public static final int MAGAZINE_UNJAM = Y_BUTTON;
        // public static final int SLIDE_UP = 0;
        // public static final int WINCH = 0;
        // public static final int CONTPANE_UP = 5;
        // public static final int CONTPANE_SPIN = 7;
}