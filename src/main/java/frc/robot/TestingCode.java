package frc.robot;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import frc.robot.Paths.AutoPaths;

public class TestingCode {


    public static void main(String[] args) {
        try {
            AutoPaths aPaths = new AutoPaths();
            aPaths.autoPathGroup("PathWeaver\\Groups\\GrabBalls");
            
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}