package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanel extends SubsystemBase {
    TalonSRX wheelSpinner;
    DoubleSolenoid wheelExtender;

    public ControlPanel() {
        initMotors();
        initSolenoids();
    }

    public void initMotors() {
        wheelSpinner = new TalonSRX(RobotMap.CONTPANE_SPIN);
    }

    public void initSolenoids() {
        wheelExtender = new DoubleSolenoid(RobotMap.CONTPANE_EXTEND_A, RobotMap.CONTPANE_EXTEND_B);
    }

    public void extend(boolean isExtend) {
        if (isExtend == true) {
            wheelExtender.set(value.kForward);
        } else if (isExtend == false) {
            wheelExtender.set(value.kReverse);
        }
    }

    public void panelSpin() {
        //Change this until the wheel spins clockwise so the panel spins clockwise
        //This way we just need to find out what color is in what position when the correct color is under the sensor
        //And use that as our target color from the color sensor when we have to spin the panel to a specific color
        wheelSpinner.set(ControlMode.PercentOutput, Constants.CONTPANE_SPEED);
    }
}