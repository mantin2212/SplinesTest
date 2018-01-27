package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import routes.ArgPoint;
import utils.Point;

public class RobotLocation {

	private ArgPoint location;

	private Supplier<Double> getTime;

	private Supplier<Double> getXAcceleration;
	private Supplier<Double> getYAcceleration;

	private Point acceleration;

	public RobotLocation(ArgPoint initLocation, Supplier<Double> getTime, Supplier<Double> getXAcceleration,
			Supplier<Double> getYAcceleration) {

		this.location = new ArgPoint(initLocation);

		this.getTime = getTime;

		this.getXAcceleration = getXAcceleration;
		this.getYAcceleration = getYAcceleration;

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
