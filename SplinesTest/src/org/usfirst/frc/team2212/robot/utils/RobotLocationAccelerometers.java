package org.usfirst.frc.team2212.robot.utils;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import routes.ArgPoint;
import utils.Point;

public class RobotLocationAccelerometers {

	private Timer timer;

	private double lastTime;
	private double time;

	private Supplier<Double> getXAcceleration;
	private Supplier<Double> getYAcceleration;

	private Point velocity;
	private Point lastVelocity;

	private Point acceleration;
	private ArgPoint location;

	public RobotLocationAccelerometers(ArgPoint initLocation, Supplier<Double> getXAcceleration, Supplier<Double> getYAcceleration) {

		this.getXAcceleration = getXAcceleration;
		this.getYAcceleration = getYAcceleration;

		this.timer = new Timer();

		this.acceleration = new Point(0, 0);
		this.location = new ArgPoint(initLocation);

		this.velocity = new Point(0, 0);
		this.lastVelocity = new Point(0, 0);
	}

	public void start() {
		time = 0;
		lastTime = 0;

		timer.start();
	}

	public void update() {

		acceleration.setXAndY(getXAcceleration.get(), getYAcceleration.get());

		updateVelocity();
	}

	private double getTimeDiff() {
		// updating time and lastTime
		lastTime = time;
		time = timer.get();

		return time - lastTime;
	}

	private void updateVelocity() {
		double time = getTimeDiff();

		this.lastVelocity.setXAndY(velocity.getX(), velocity.getY());
		this.velocity.move(time * acceleration.getX(), time * acceleration.getY());
	}

	private void updateLocation() {
		double time = getTimeDiff();
		this.velocity.move(time * acceleration.getX(), time * acceleration.getY());
	}

	public ArgPoint getLocation() {
		return location;
	}

}
