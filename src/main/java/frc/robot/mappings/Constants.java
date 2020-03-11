package frc.robot.mappings;

public class Constants {
    //(multiplying by 0.0254 converts inches to meters)
    public static final double INCHES_TO_METER = 0.0254;

    // Drive Constants
    public static final double RAMP_RATE = 0.25;

    public static final double  DRIVE_HIGH  = 1.0;
    public static final double  DRIVE_LOW   = 0.65;
    
    public static final double  TURN_SPEED  = 0.65;

    public static final double STEER_K = -0.0275;
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
    public static final double ksVolts = 0.208;
    public static final double kvVoltSecondsPerMeter = 0.201;
    public static final double kaVoltSecondsSquaredPerMeter = 0.0208;

    public static final double kPDriveVel = 0.359;

    public final static double kRamseteB = 2;
    public final static double kRamseteZeta = 0.7;

    //shooter constansts
    public static final double SHOOT_SPEED = -1.0;
    public static final double LOW_SHOOT_SPEED = -0.25;
    public static final double IDLE_SPEED = 0;
    public static final double FLY_WHEEL_ENCODER_COUNT = 1116;
    public static final double FLY_WHEEL_RADIUS = 0 * INCHES_TO_METER; 

    //intake constants
    public static final double INTAKE_SPEED = -0.45;

    //climb constants
    public static final double SLIDE_SPEED = 0.25;
    public static final double WINCH_SPEED = 0.25;

    //control panel constants
    public static final double CONTPANE_SPEED = 0.25;

    //limelight constants
    public static final double LIMELIGHT_MOUNTING_HEIGHT = 0;
    public static final double LIMELIGHT_MOUNTING_ANGLE = 0;

    //field constants
    public static final double POWERPPORT_HEIGHT = 98.25 * INCHES_TO_METER;
}