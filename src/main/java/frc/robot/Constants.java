package frc.robot;

public class Constants {
    // List of constant values to use in place of raw values;
    // use these instead of exact values to keep changes consistent

    //(multiplying by 0.0254 converts inches to meters)
    public static final double INCHES_TO_METER = 0.0254;

    // Drive Constants
    public static final double RAMP_RATE = 0.25;

    public static final double  DRIVE_HIGH  = 1.0;
    public static final double  DRIVE_LOW   = 0.65;
    
    public static final double  TURN_SPEED  = 0.55;

    //These are based off of last years robot and probably need to be changed
    public static final double STEER_K = 0.04;
    public static final double DRIVE_K = 0.26;


    //wheel diameter in meter 
    public static final double  Wheel_Diameter  = 8 * INCHES_TO_METER;
    static final double     DRIVE_LOW_GEAR_REDUCTION    = (40/12)*(60/27)*(64/20) ; 
    static final double     DRIVE_HIGH_GEAR_REDUCTION    = (40/12)*(44/40)*(64/20) ;

    //Encoder Constants
    public static final int     countsPerRev    = 4096; 
    public static final double  CIRCUMFERENCE  = Wheel_Diameter*Math.PI;
    public static final double  countsPerMeter  = (countsPerRev * DRIVE_LOW_GEAR_REDUCTION) /(CIRCUMFERENCE );


    //Feedforward/Feedback Gains
    // TODO USE The Robot Characterization Toolsuite to obtaining these values
    public static final double ksVolts = 0.208;
    public static final double kvVoltSecondsPerMeter = 0.201;
    public static final double kaVoltSecondsSquaredPerMeter = 0.0208;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 0.359;

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        //for more information on tuning these values (if it is required), see 
        //https://docs.wpilib.org/en/latest/docs/software/advanced-control/trajectories/ramsete.html#constructing-the-ramsete-controller-object
        public final static double kRamseteB = 2;
        public final static double kRamseteZeta = 0.7;

    //shooter constansts
	public static final double SHOOT_SPEED = -1.0;
    public static final double IDLE_SPEED = 0;
    public static final double FLY_WHEEL_ENCODER_COUNT = 1116;
    public static final double FLY_WHEEL_RADIUS = 0 * INCHES_TO_METER; 

    //intake constants
    public static final double INTAKE_SPEED = -0.35;

    //climb constants
    public static final double SLIDE_SPEED = 0.25;
    public static final double WINCH_SPEED = 0.25;

    //control panel constants
    public static final double CONTPANE_SPEED = 0.25;

    //limelight constants
    public static final double LIMELIGHT_MOUNTING_HEIGHT = 0;
    public static final double LIMELIGHT_MOUNTING_ANGLE = 0;

    //field constants
    public static final double POWERPPORT_HEIGHT = 98.25 * 0.0254;
}