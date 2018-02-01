package org.usfirst.frc.team2212.robot.utils;

import utils.Point;

public class Utils {

	public static double getVoltage(double velocity, double acceleration) {
		return (Constants.MOTOR_K2 * velocity + acceleration) / Constants.MOTOR_K1;
	}

	public static double getNormalizedVoltage(double velocity, double acceleration) {
		return getVoltage(velocity, acceleration) / Constants.MAX_ABSOLUTE_VOLTAGE;
	}

	public static Point getAbsoluteVector(Point relativeVector, double angle) {

		double relativeX = relativeVector.getX();
		double relativeY = relativeVector.getY();

		// calculating the absolute axis values of the vector
		double absoluteX = relativeY * Math.sin(angle) + relativeX * Math.cos(angle);
		double absoluteY = relativeY * Math.cos(angle) - relativeX * Math.sin(angle);

		return new Point(absoluteX, absoluteY);
	}

}
