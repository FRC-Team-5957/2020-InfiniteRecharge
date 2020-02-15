package frc.robot.Paths;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.subsystems.ShiftingWestCoast;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * AutoPaths
 */
public class AutoPaths {


  private ShiftingWestCoast drive;
  private Trajectory trajectory;
  DifferentialDriveKinematics kDriveKinematics;

  AutoPaths() throws FileNotFoundException, IOException, ParseException {
    drive = new ShiftingWestCoast();

    // parsing file "pathweaver.json"
    Object obj = new JSONParser().parse(new FileReader("PathWeaver\\pathweaver.json"));

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;


    //everything is in meters
    // getting maxVelocity, maxAcceleration, and wheelBase (distance of the left and right wheels in meters)
    final double maxVelocity = (double) jo.get("maxVelocity");
    final double maxAcceleration = (double) jo.get("maxAcceleration");
    final double wheelBase = (double) jo.get("wheelBase");
    kDriveKinematics = new DifferentialDriveKinematics(wheelBase);

         // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
    new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(Constants.ksVolts,
                                   Constants.kvVoltSecondsPerMeter,
                                   Constants.kaVoltSecondsSquaredPerMeter),
        kDriveKinematics,
        10);

            // Create config for trajectory
    TrajectoryConfig config =
    new TrajectoryConfig(maxVelocity,
      maxAcceleration)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(kDriveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);

  }

    

    public Trajectory getTrajectory(String trajectoryJSON ){
try {
  Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
   this.trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
} catch (IOException ex) {
  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
}
  return trajectory;
    }

    public void autoMove(Trajectory trajectory) {
      RamseteCommand ramseteCommand = new RamseteCommand(
        trajectory,
        drive::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(Constants.ksVolts,
                                   Constants.kvVoltSecondsPerMeter,
                                   Constants.kaVoltSecondsSquaredPerMeter),
        kDriveKinematics,
        drive::getWheelSpeeds,
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        drive::tankDriveVolts,
        drive
    );
    }
}