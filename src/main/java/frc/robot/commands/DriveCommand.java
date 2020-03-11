package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.mappings.DS;

public class DriveCommand extends CommandBase {
    private final Drivetrain drivetrain;
    // private final double speed;
    private final DoubleSupplier speed;
    private final DoubleSupplier turn;
    private final BooleanSupplier shift;


    public DriveCommand(Drivetrain subsytem, DoubleSupplier turn, DoubleSupplier speed, BooleanSupplier shift) {
        drivetrain = subsytem;
        this.speed = speed;
        this.turn = turn;
        this.shift = shift;
        
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        drivetrain.drive(speed.getAsDouble(), turn.getAsDouble(), shift.getAsBoolean());
        // drivetrain.setSpeed(speed.getAsDouble());shi
    }

    @Override
    public void end(boolean interrupted) {
        
    }
}