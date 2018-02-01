package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import routes.ArgPoint;
import utils.Point;

public class DriveTankByRouteUpdating extends Command {

	private ArgPoint target;

	private Command currentCommand;

	private double fixTime;

	private double tolerance;

	public DriveTankByRouteUpdating(ArgPoint target, double fixTime, double tolerance) {

		this.fixTime = fixTime;

		requires(Robot.drivetrain);

		this.tolerance = tolerance;

		currentCommand = new DriveTankByRoute(target, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		currentCommand.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!currentCommand.isRunning()) {
			currentCommand = new DriveTankByRoute(target, fixTime);
			currentCommand.start();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Point.distance(Robot.location.getLocation(), target) <= tolerance;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
