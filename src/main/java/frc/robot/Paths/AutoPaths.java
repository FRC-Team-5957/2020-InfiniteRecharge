package frc.robot.Paths;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

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


  AutoPaths() throws FileNotFoundException, IOException, ParseException {
    // parsing file "pathweaver.json"
    Object obj = new JSONParser().parse(new FileReader("PathWeaver\\pathweaver.json"));

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;

    //everything is in meters
    // getting maxVelocity, maxAcceleration, and wheelBase (distance of the left and right wheels in meters)
    final double maxVelocity = (double) jo.get("maxVelocity");
    final double maxAcceleration = (double) jo.get("maxAcceleration");
    final double wheelBase = (double) jo.get("wheelBase");
    final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(wheelBase);

        

  }

    

    public void moveOfTheLine(){
        
        String trajectoryJSON = "2020-InfiniteRecharge\\PathWeaver\\output\\Forward.wpilib.json";
try {
  Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
  Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
} catch (IOException ex) {
  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
}
    }
}