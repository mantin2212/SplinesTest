package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import routes.ArgPoint;
import utils.Point;

public class RobotLocation {

	private ArgPoint location;

	private double prevDistance;
	private double prevAngle;

	private Supplier<Double> getAngle;
	private Supplier<Double> getDistance;

	public RobotLocation(ArgPoint initLocation, double dt, Supplier<Double> getAngle, Supplier<Double> getDistance) {
		// initializing the robot's location
		this.location = new ArgPoint(initLocation);
		// initializing class variables
		this.getAngle = getAngle;
		this.getDistance = getDistance;
		// setting the previous distance and angle to their values
		this.prevDistance = getDistance.get();
		this.prevAngle = getAngle.get();
	}

	public void update() {
		// calculating the current distance and angle
		double currentDistance = getDistance.get();
		double currentAngle = getAngle.get();

		// setting the angle of the robot's location
		location.setAngle(currentAngle);

		// setting the location's coordinates
		Point difference = getDifference(prevAngle, currentAngle, currentDistance - prevDistance);
		location.move(difference.getX(), difference.getY());

		// updating class variables
		prevDistance = currentDistance;
		prevAngle = currentAngle;
	}

	public ArgPoint getLocation() {
		return location;
	}

	private Point getDifference(double previousAngle, double currentAngle, double distance) {
		// calculating the x and y values of the difference
		double dx = getDx(previousAngle, currentAngle, distance);
		double dy = getDy(previousAngle, currentAngle, distance);

		return new Point(dx, dy);
	}

	private double getDx(double previousAngle, double currentAngle, double distance) {
		double r = distance / (currentAngle - previousAngle);

		double result = -2 * r * Math.sin((currentAngle - previousAngle) / 2)
				* Math.cos((currentAngle + previousAngle) / 2);

		return result;
	}

	private double getDy(double previousAngle, double currentAngle, double distance) {
		double r = distance / (currentAngle - previousAngle);

		double result = -2 * r * Math.sin((currentAngle - previousAngle) / 2)
				* Math.sin((currentAngle + previousAngle) / 2);

		return result;
	}
}
