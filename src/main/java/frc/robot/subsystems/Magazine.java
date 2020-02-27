package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Magazine extends SubsystemBase {
    CANSparkMax magBot, magTop;

    public Magazine() {
        initMotors();
    }

    public void initMotors() {

        magBot = new CANSparkMax(RobotMap.MAGBOT_BELT, MotorType.kBrushless);
        magTop = new CANSparkMax(RobotMap.MAGTOP_BELT, MotorType.kBrushless);
    }

    public void advance() {
        magBot.set(Constants.INTAKE_SPEED);
        magTop.set(Constants.INTAKE_SPEED);
    }
    //Add in pneumatic pistons 
}