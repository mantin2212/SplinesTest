package org.usfirst.frc.team2212.robot.utils.location;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.utils.Utils;

import routes.ArgPoint;
import utils.Point;

public class RobotLocationAccelerometers extends RobotLocation {

	private Supplier<Double> getSideAcceleration;
	private Supplier<Double> getForwardAcceleration;

	private Point velocity;
	private Point lastVelocity;

	private Point lastAcceleration;
	private Point acceleration;

	private Point relativeAcceleration;

	public RobotLocationAccelerometers(ArgPoint initLocation, Supplier<Double> getRelativeTime,
			Supplier<Double> getSideAcceleration, Supplier<Double> getForwardAcceleration) {

		super(initLocation, getRelativeTime);

		this.getSideAcceleration = getSideAcceleration;
		this.getForwardAcceleration = getForwardAcceleration;

		this.acceleration = new Point(0, 0);

		this.velocity = new Point(0, 0);
	}

	public void updateData() {
		super.updateData();

		// receiving the relative acceleration from the suppliers
		relativeAcceleration.setXAndY(getSideAcceleration.get(), getForwardAcceleration.get());

		// updating the last acceleration and velocity
		updatePrevs();

		// calculating the velocity vector and the robot's angle
		this.velocity.move(timeDiff * lastAcceleration.getX(), timeDiff * lastAcceleration.getY());

		location.setAngle(Math.atan2(velocity.getY(), velocity.getX()));

		// calculating the absolute acceleration vector
		acceleration = Utils.getAbsoluteVector(relativeAcceleration, location.getAngle());
	}

	private void updatePrevs() {

		this.lastVelocity.setXAndY(velocity.getX(), velocity.getY());

		this.lastAcceleration.setXAndY(acceleration.getX(), acceleration.getY());
	}

	protected void updateLocation() {
		// calculating the dx and dy values
		double dx = timeDiff * velocity.getX() - 0.5 * Math.pow(timeDiff, 2) * lastAcceleration.getX();
		double dy = timeDiff * velocity.getY() - 0.5 * Math.pow(timeDiff, 2) * lastAcceleration.getY();

		this.velocity.move(dx, dy);
	}

	public ArgPoint getLocation() {
		return location;
	}
	// matan was here
}
