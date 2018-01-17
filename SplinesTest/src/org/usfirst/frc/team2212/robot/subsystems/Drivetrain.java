package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drivetrain extends TankDrivetrain {

	private SpeedController left, right;
	private Encoder leftEncoder, rightEncoder;
	private Gyro gyro;

	public Drivetrain(SpeedController right, SpeedController left, Encoder leftEncoder, Encoder rightEncoder,
			Gyro gyro) {
		this.left = left;
		this.right = right;

		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;

		this.gyro = gyro;

		gyro.calibrate();
		right.setInverted(true);
	}

	@Override
	public PIDSource getLeftPIDSource() {
		return leftEncoder;
	}

	@Override
	public PIDSource getRightPIDSource() {
		return rightEncoder;
	}

	@Override
	public void setLeft(double speed) {
		left.set(speed);
	}

	public double getRate() {
		return gyro.getRate();
	}

	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	@Override
	public void setRight(double speed) {
		right.set(speed);
	}

	public void initDefaultCommand() {
	}

}
