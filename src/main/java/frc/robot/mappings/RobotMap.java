package frc.robot.mappings;

public class RobotMap {
    // ID values for various parts of the robot

    // DS IDs
    public static final int DRIVER_USB = 0;
    public static final int OPERATOR_USB = 1;

    //Motor DS IDs
    public static final int DRIVE_RIGHT_MASTER = 1; 
    public static final int DRIVE_RIGHT_SLAVE = 2;  
    public static final int DRIVE_LEFT_MASTER = 3;  
    public static final int DRIVE_LEFT_SLAVE = 4;   

    // Intake mc ID
    public static final int INTAKE_ID = 8;

    // Magazine mc IDs
    public static final int MAGTOP_BELT = 5;
    public static final int MAGBOT_BELT = 6;

    // Shooter Id
    public static final int SHOOTER_ID = 7;  

    // Control Panel mc ID
    public static final int CONTPANE_SPIN = 9;

    //Pnuematics
    public static final int PCM_ID = 10;
    public static final int DRIVE_SHIFT_A = 0;
    public static final int DRIVE_SHIFT_B = 1;
    public static final int INTAKE_EXTEND_A = 6;
    public static final int INTAKE_EXTEND_B = 7;
    public static final int CONTPANE_EXTEND_A = 2;
    public static final int CONTPANE_EXTEND_B = 3;
    public static final int MAG_SOMETHING_A = 4;
    public static final int MAG_SOMETHING_B = 5;
    //THIS IS THE MAX AMOUNT OF SOLENOIDS THERE IS NO MORE SPACE ON THE PCM WE ARE NOT ADDING ANYMORE SOLENOIDS
}