package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.mappings.Constants;
import frc.robot.mappings.RobotMap;

public class Drivetrain extends SubsystemBase {
    private DoubleSolenoid driveShift;
    private CANSparkMax leftMaster;
    private CANSparkMax leftSlave;
    private CANSparkMax rightMaster;
    private CANSparkMax rightSlave;

    private int currentLimit = 60;

    private DifferentialDrive drive;

    public Drivetrain() {
        initMotors();
        initShift();
    }

    public void initMotors() {
        leftMaster = new CANSparkMax(RobotMap.DRIVE_LEFT_MASTER, MotorType.kBrushless);
        leftSlave = new CANSparkMax(RobotMap.DRIVE_LEFT_SLAVE, MotorType.kBrushless);
        rightMaster = new CANSparkMax(RobotMap.DRIVE_RIGHT_MASTER, MotorType.kBrushless);
        rightSlave = new CANSparkMax(RobotMap.DRIVE_RIGHT_SLAVE, MotorType.kBrushless);

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        drive = new DifferentialDrive(leftMaster, rightMaster);
        drive.setMaxOutput(Constants.DRIVE_HIGH);
    }

    public void initShift() {
        driveShift = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_A, RobotMap.DRIVE_SHIFT_B);
    }

    public void drive(double speed, double turn, boolean shift) {
        double speedInput = speed;
        double turnInput = turn;

        drive.curvatureDrive(speedInput, turnInput, true);
        shift(shift);
    }

    public void setSpeed(double speed) {
        leftMaster.set(speed);
        rightMaster.set(speed);
    }

    public void shift(boolean shift) {
        if (shift) {
            driveShift.set(Value.kForward);
        } else {
            driveShift.set(Value.kReverse);
        }
    }
}