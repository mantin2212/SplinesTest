package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import routes.ArgPoint;
import utils.Point;

public class RobotLocation {

	private ArgPoint location;

	private double distance;
	private double dt;

	private Supplier<Double> getAngle;
	private Supplier<Double> getDistance;

	public RobotLocation(ArgPoint initLocation, double dt, Supplier<Double> getAngle, Supplier<Double> getDistance) {

		this.location = new ArgPoint(initLocation);

		this.dt = dt;
		this.distance = 0;

		this.getAngle = getAngle;
		this.getDistance = getDistance;
	}

	public void update() {
		double dl = getDistance.get() - distance;
		distance += dl;

		location.setAngle(getAngle.get());

		location.move(dl * Math.cos(location.getAngle()), dl * Math.sin(location.getAngle()));
	}

	public ArgPoint getLocation() {
		return location;
	}

}
