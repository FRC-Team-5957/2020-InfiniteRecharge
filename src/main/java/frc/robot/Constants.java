package frc.robot;

public class Constants {
    // List of constant values to use in place of raw values;
    // use these instead of exact values to keep changes consistent

    // Drive Constants
    public static final double RAMP_RATE = 0.25;

    public static final double  DRIVE_HIGH  = 1.0;
    public static final double  DRIVE_LOW   = 0.65;
    
    public static final double  TURN_SPEED  = 0.55;


    //wheel diameter in meter (multiplying by 0.0254 converts inches to meters)
    public static final double  Wheel_Diameter  = 8 * 0.0254;
    static final double     DRIVE_LOW_GEAR_REDUCTION    = (40/12)*(60/27)*(64/20) ; 
    static final double     DRIVE_HIGH_GEAR_REDUCTION    = (40/12)*(44/40)*(64/20) ;

    //Encoder Constants
    public static final int     countsPerRev    = 4096;
    public static final double  distancePerRev  = Wheel_Diameter*Math.PI;
    public static final double  countsPerMeter  = (countsPerRev * DRIVE_LOW_GEAR_REDUCTION) /(distancePerRev);


    //Feedforward/Feedback Gains
    // TODO USE The Robot Characterization Toolsuite to obtaining these values
    public static final double ksVolts = 0;
    public static final double kvVoltSecondsPerMeter = 0;
    public static final double kaVoltSecondsSquaredPerMeter = 0;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 0;

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        //for more information on tuning these values (if it is required), see 
        //https://docs.wpilib.org/en/latest/docs/software/advanced-control/trajectories/ramsete.html#constructing-the-ramsete-controller-object
        public final static double kRamseteB = 2;
        public final static double kRamseteZeta = 0.7;

    //shooter constansts
	public static final double SHOOT_SPEED = 1.0;
	public static final double IDLE_SPEED = 0;

    //intake constants
    public static final double INTAKE_SPEED = 0.5;

    //climb constants
    public static final double SLIDE_SPEED = 0.5;
    public static final double WINCH_SPEED = 0.5;

    //control panel constants
    public static final double CONTPANE_SPEED = 0.5;
}