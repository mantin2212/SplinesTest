package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import routes.ArgPoint;

public abstract class RobotLocation {

	protected Supplier<Double> getRelativeTime;
	private double lastTime;
	protected double timeDiff;

	protected ArgPoint location;

	public RobotLocation(ArgPoint initLocation, Supplier<Double> getRelativeTime) {

		this.location = new ArgPoint(location);
		this.getRelativeTime = getRelativeTime;

		lastTime = 0;
	}

	public ArgPoint getLocation() {
		return location;
	}

	public void update() {
		updateData();

		updateLocation();
	}

	protected void updateData() {
		updateTime();
	}

	private void updateTime() {
		double currentTime = getRelativeTime.get();

		timeDiff = currentTime - lastTime;
		lastTime = currentTime;
	}

	protected abstract void updateLocation();
}
