package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.utils.Constants;
import org.usfirst.frc.team2212.robot.utils.Utils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import routes.ArgPoint;
import routes.routes.Route;
import routes.routes.RouteDescription;
import routes.routes.SplineDescription;
import routes.synchronizing.DriveMaxSpeeds;
import routes.synchronizing.RouteSynchronizer;
import routes.synchronizing.RouteSynchronizer.Side;

public class DriveTankByRoute extends Command {

	private RouteSynchronizer synchronizer;
	private Timer timer;

	public DriveTankByRoute(ArgPoint target, double timeout) {

		this.setTimeout(timeout);

		RouteDescription desc = new SplineDescription(Robot.location.getLocation(), target, Constants.K);

		synchronizer = new DriveMaxSpeeds(Route.getRoute(desc, Constants.N), Constants.ROBOT_WIDTH,
				Constants.ROBOT_MAX_VELOCITY, Constants.ROBOT_MAX_ACCELERATION);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double time = timer.get();

		double rightVoltage = Utils.getVoltage(synchronizer.getSpeed(Side.RIGHT, time), 0);
		double leftVoltage = Utils.getVoltage(synchronizer.getSpeed(Side.LEFT, time), 0);

		Robot.drivetrain.tankDrive(leftVoltage, rightVoltage);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return synchronizer.isFinished(timer.get()) || isTimedOut();
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
