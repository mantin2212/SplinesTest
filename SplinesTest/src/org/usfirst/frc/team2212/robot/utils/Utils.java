package org.usfirst.frc.team2212.robot.utils;

public class Utils {

	public static double getVoltage(double velocity, double acceleration) {
		return (Constants.MOTOR_K2 * velocity + acceleration) / Constants.MOTOR_K1;
	}
}
