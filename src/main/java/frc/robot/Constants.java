package frc.robot;

public class Constants {
    // List of constant values to use in place of raw values;
    // use these instead of exact values to keep changes consistent

    // Drive Constants
    public static final double RAMP_RATE = 0.25;

    public static final double  DRIVE_HIGH  = 1.0;
    public static final double  DRIVE_LOW   = 0.65;
    
    public static final double  TURN_SPEED  = 0.55;


    //wheel diameter in meter
    public static final double  Wheel_Diameter  = 0 * 0.0254;

    //Encoder Constants
    public static final int     countsPerRev    = 42;
    public static final double  distancePerRev  = Wheel_Diameter*Math.PI;



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
        final double kRamseteB = 2;
        final double kRamseteZeta = 0.7;
}