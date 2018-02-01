package org.usfirst.frc.team2212.robot.utils.location;

import java.util.function.Supplier;

import routes.ArgPoint;
import utils.Point;

public class RobotLocationAccelerometers extends RobotLocation {

	private Supplier<Double> getXAcceleration;
	private Supplier<Double> getYAcceleration;

	private Point velocity;
	private Point lastVelocity;

	private Point acceleration;

	public RobotLocationAccelerometers(ArgPoint initLocation, Supplier<Double> getRelativeTime,
			Supplier<Double> getXAcceleration, Supplier<Double> getYAcceleration) {

		super(initLocation, getRelativeTime);

		this.getXAcceleration = getXAcceleration;
		this.getYAcceleration = getYAcceleration;

		this.acceleration = new Point(0, 0);

		this.velocity = new Point(0, 0);
	}

	public void updateData() {
		super.updateData();

		acceleration.setXAndY(getXAcceleration.get(), getYAcceleration.get());

		updateVelocity();
	}

	private void updateVelocity() {
		this.lastVelocity.setXAndY(velocity.getX(), velocity.getY());

		this.velocity.move(timeDiff * acceleration.getX(), timeDiff * acceleration.getY());
	}

	protected void updateLocation() {
		double dx = timeDiff * velocity.getX() - 0.5 * Math.pow(timeDiff, 2) * acceleration.getX();
		double dy = timeDiff * velocity.getY() - 0.5 * Math.pow(timeDiff, 2) * acceleration.getY();

		this.velocity.move(dx, dy);
	}

	public ArgPoint getLocation() {
		return location;
	}
	// matan was here
}
