package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.utils.Constants;
import org.usfirst.frc.team2212.robot.utils.RouteHelper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import routes.ArgPoint;
import routes.routes.Route;
import routes.routes.RouteDescription;
import routes.routes.SplineDescription;

public class DriveTankByRoute extends Command {

	private RouteHelper synchronizer;

	private Timer timer;
	private ArgPoint location;

	private double tolerance;

	public DriveTankByRoute(ArgPoint target, double speed, double tolerance, double timeout) {

		this.setTimeout(timeout);
		this.tolerance = tolerance;

		this.location = Robot.location.getLocation();

		RouteDescription desc = new SplineDescription(location, target, Constants.K);
		synchronizer = new RouteHelper(Route.getRoute(desc, Constants.N), speed, Constants.ROBOT_WIDTH);

		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		location = Robot.location.getLocation();

		Robot.drivetrain.tankDrive(synchronizer.getLeftVoltage(location, tolerance),
				synchronizer.getRightVoltage(location, tolerance));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return synchronizer.isFinished(location, tolerance) || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}