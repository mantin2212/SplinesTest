package org.usfirst.frc.team2212.robot.utils;

import routes.RoutePointData;
import utils.Point;

public class RouteHelper {

	private RoutePointData[] route;

	private double acceleration;
	private double voltage;

	private double totalTime;
	private double robotWidth;

	public RouteHelper(RoutePointData[] route, double acceleration, double wantedVoltage, double totalTime,
			double robotWidth) {

		this.acceleration = acceleration;
		this.voltage = wantedVoltage;
		this.totalTime = totalTime;
		this.robotWidth = robotWidth;
	}

	public double getVoltage(double time) {
		if (Math.min(time, totalTime - time) >= voltage / acceleration)
			return voltage;
		else
			return acceleration * Math.min(time, totalTime - time);
	}

	public double getRightVoltage(Point location, double time, double tolerance) {
		int index = getIndex(location, tolerance);

		double radius = route[index].getRadius();
		double voltage1 = getVoltage(time);

		if (radius == 0)
			return voltage1;
		else
			return ((radius - robotWidth / 2) / radius) * voltage1;
	}

	public double getLeftVoltage(Point location, double time, double tolerance) {
		int index = getIndex(location, tolerance);

		double radius = route[index].getRadius();
		double voltage1 = getVoltage(time);

		if (radius == 0)
			return voltage1;
		else
			return ((radius + robotWidth / 2) / radius) * voltage1;
	}

	private int getIndex(Point location, double tolerance) {
		for (int i = 0; i < route.length; i++) {
			if (Point.distance(location, route[i]) <= tolerance)
				return i;
		}
		return -1;
	}
}
