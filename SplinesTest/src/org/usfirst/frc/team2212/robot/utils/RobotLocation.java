package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import utils.Point;

public class RobotLocation {

	private Point location;
	private double arg;

	private double distance;
	private double dt;

	private Supplier<Double> getRate;
	private Supplier<Double> getDistance;

	public RobotLocation(Point initLocation, double initAngle, double dt, Supplier<Double> getRate,
			Supplier<Double> getDistance) {
		this.location = new Point(initLocation);
		this.arg = initAngle;

		this.dt = dt;
		this.distance = 0;

		this.getRate = getRate;
		this.getDistance = getDistance;
	}

	public void update() {
		double dl = getDistance.get() - distance;
		distance += dl;

		arg += dt * getRate.get();

		location.move(dl * Math.cos(arg), dl * Math.sin(arg));
	}

	public Point getLocation() {
		return location;
	}

	public double getArg() {
		return arg;
	}
}
