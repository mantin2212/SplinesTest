package org.usfirst.frc.team2212.robot.utils;

import routes.RoutePointData;
import utils.Point;

public class RouteHelper {

	private RoutePointData[] route;

	private double voltage;

	private double robotWidth;

	public RouteHelper(RoutePointData[] route, double wantedVoltage, double robotWidth) {

		this.voltage = wantedVoltage;
		this.robotWidth = robotWidth;
	}

	public boolean isFinished(Point location, double tolerance) {
		return (Point.distance(location, route[route.length - 1]) <= tolerance);
	}

	public double getRightVoltage(Point location, double tolerance) {
		int index = getIndex(location, tolerance);

		double radius = route[index].getRadius();

		if (radius == 0)
			return voltage;
		else
			return ((radius - robotWidth / 2) / radius) * voltage;
	}

	public double getLeftVoltage(Point location, double tolerance) {
		int index = getIndex(location, tolerance);

		double radius = route[index].getRadius();

		if (radius == 0)
			return voltage;
		else
			return ((radius + robotWidth / 2) / radius) * voltage;
	}

	private int getIndex(Point location, double tolerance) {
		for (int i = 0; i < route.length; i++) {
			if (Point.distance(location, route[i]) <= tolerance)
				return i;
		}
		return -1;
	}
}
