package org.usfirst.frc.team2212.robot.utils;

import routes.ArgPoint;
import utils.Point;

public class Constants {

	public static final ArgPoint ROBOT_INIT_LOCATION = new ArgPoint(new Point(0, 0), 0);;

	// cm
	public static final ArgPoint ROUTES_TARGET = new ArgPoint(new Point(200, 100), 0);

	public static final int K = 800;

	public static final int N = 1000;

	public static final double ROBOT_MAX_VELOCITY = 10;
	public static final double ROBOT_MAX_ACCELERATION = 10;

	public static final double ROBOT_WIDTH = 60;

	public static final double MOTOR_K1 = 0;
	public static final double MOTOR_K2 = 0;

	public static final double MAX_ABSOLUTE_VOLTAGE = 6;
}
