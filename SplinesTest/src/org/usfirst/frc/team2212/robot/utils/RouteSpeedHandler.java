package org.usfirst.frc.team2212.robot.utils;

import routes.Route;
import routes.RoutePoint;
import utils.Point;

public class RouteSpeedHandler {

	public static final double MAX_LINEAR_VELOCITY = 1;
	public static final double MAX_LINEAR_ACCELERATION = 1;
	public static final double ROBOT_WIDTH = 2;

	private RoutePoint[] routePoints;
	private int n;
	private double[] maxSpeeds;

	public RouteSpeedHandler(Route route, int n) {
		this.n = n;
		setMaxSpeeds();
	}

	public double getMaxSpeed(int k) {
		return maxSpeeds[k];
	}

	private double getMaxSideSpeed(double radius) {
		return Math.abs(radius) / (Math.abs(radius) + ROBOT_WIDTH / 2) * MAX_LINEAR_VELOCITY;
	}

	private void fixMaxSideSpeeds() {
		for (int k = 0; k <= n; k++) {
			maxSpeeds[k] = Math.min(maxSpeeds[k], getMaxSideSpeed(routePoints[k].getRadius()));
		}
	}

	private void setMaxSpeeds() {
		maxSpeeds = new double[n + 1];

		for (int k = 0; k <= n; k++) {

		}
	}

	private void fixAcceleration() {
		maxSpeeds[0] = 0;

		for (int k = 1; k <= n; k++) {
			maxSpeeds[k] = Math.min(maxSpeeds[k], getSpeed(MAX_LINEAR_ACCELERATION, maxSpeeds[k - 1]));
		}

		maxSpeeds[n] = 0;
		for (int k = n - 1; k >= 0; k--) {
			maxSpeeds[k] = Math.min(maxSpeeds[k], getSpeed(-MAX_LINEAR_ACCELERATION, maxSpeeds[k + 1]));
		}
	}

	private double getSpeed(double acceleration, double prevSpeed, double distance) {
		return Math.sqrt(prevSpeed * prevSpeed + 2 * acceleration * distance);
	}
}
