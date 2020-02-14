package frc.robot.Paths;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

/**
 * AutoPaths
 */
public class AutoPaths {

    

    public void moveOfTheLine(){
        
        String trajectoryJSON = "C:\\Users\\Cat 5\\Desktop\\2020\\2020-InfiniteRecharge\\PathWeaver\\output\\Forward.wpilib.json";
try {
  Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
  Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
} catch (IOException ex) {
  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
}
    }
}