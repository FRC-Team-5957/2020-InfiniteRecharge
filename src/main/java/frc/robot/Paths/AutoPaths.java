package frc.robot.Paths;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.subsystems.ShiftingWestCoast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * AutoPaths
 */
public class AutoPaths {


  private ShiftingWestCoast drive;
  private Trajectory trajectory;
  private JSONObject jo;
  DifferentialDriveKinematics kDriveKinematics;

  public AutoPaths(ShiftingWestCoast subsystem) throws FileNotFoundException, IOException, ParseException {
    drive = subsystem;

    // parsing file "pathweaver.json"
    Object obj = new JSONParser().parse(new FileReader("PathWeaver\\pathweaver.json"));

    // typecasting obj to JSONObject
    jo = (JSONObject) obj;



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


    
  public void autoPathGroup(String groupPath){
    File file = new File(groupPath);
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
        String pathPath = "PathWeaver\\output\\" + line + ".wpilib.json" ;
        System.out.println(pathPath);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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

    public SequentialCommandGroup autoMove(String trajectoryJSON) {
      Trajectory trajectory = getTrajectory(trajectoryJSON);
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

    return ramseteCommand.andThen(() -> drive.tankDriveVolts(0, 0));
    }
}