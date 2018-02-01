package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import routes.ArgPoint;

public class RobotLocationEncoders extends RobotLocation {

	private ArgPoint location;

	private double prevDistance;
	private double distanceDiff;

	private double prevAngle;
	private double angle;

	private Supplier<Double> getAngle;
	private Supplier<Double> getDistance;

	public RobotLocationEncoders(ArgPoint initLocation, Supplier<Double> getRelativeTime, Supplier<Double> getAngle,
			Supplier<Double> getDistance) {
		super(initLocation, getRelativeTime);

		// initializing class variables
		this.getAngle = getAngle;
		this.getDistance = getDistance;
	}

	@Override
	protected void updateData() {
		super.updateData();

		updateAngle();
		updateDistance();
	}

	private void updateDistance() {
		double currentDistance = getDistance.get();

		distanceDiff = currentDistance - prevDistance;
		prevDistance = currentDistance;
	}

	private void updateAngle() {
		prevAngle = angle;
		angle = getAngle.get();
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

	public double getVelocity() {
		return (getDistance.get() - prevDistance) / timeDiff;
	}

	@Override
	protected void updateLocation() {
		// calculating the x and y values of the difference
		double dx = getDx(prevAngle, angle, distanceDiff);
		double dy = getDy(prevAngle, angle, distanceDiff);

		location.move(dx, dy);
	}

}
