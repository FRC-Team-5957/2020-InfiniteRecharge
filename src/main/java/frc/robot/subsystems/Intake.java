package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    VictorSPX intakeMotor;
    DoubleSolenoid extendSol;

    public Intake() {
        initMotor();
        initExtend();
    }

    public void initMotor() {
        intakeMotor = new VictorSPX(RobotMap.INTAKE_ID);
    }

    public void initExtend() {
        extendSol = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INTAKE_EXTEND_A, RobotMap.INTAKE_EXTEND_B);
    }

    public void intake() {
        intakeMotor.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
    }

    public void stopoIntake() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    // public void extend(boolean isExtend) {
    //     if (isExtend == true) {
    //         extendSol.set(Value.kForward);
    //     } else if (isExtend == false) {
    //         extendSol.set(Value.kReverse);
    //     }
    // }

    public void extend() {
        extendSol.set(Value.kForward);
    }

    public void retract() {
        extendSol.set(Value.kReverse);
    }
}


