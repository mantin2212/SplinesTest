
package org.usfirst.frc.team2212.robot;

import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2212.robot.utils.Constants;
import org.usfirst.frc.team2212.robot.utils.RobotLocation;
import org.usfirst.frc.team2212.robot.utils.RobotLocationAccelerometers;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static RobotLocation location;

	public static Accelerometer acc;

	public static DashBoardController dbc;

	@Override
	public void robotInit() {
		// initializing gearboxes and encoders
		DoubleSpeedcontroller left = new DoubleSpeedcontroller(new WPI_TalonSRX(RobotMap.LEFT_FRONT_PORT),
				new WPI_TalonSRX(RobotMap.LEFT_REAR_PORT));
		DoubleSpeedcontroller right = new DoubleSpeedcontroller(new WPI_TalonSRX(RobotMap.RIGHT_FRONT_PORT),
				new WPI_TalonSRX(RobotMap.RIGHT_REAR_PORT));

		Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORT_A, RobotMap.LEFT_ENCODER_PORT_B);
		Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORT_A, RobotMap.RIGHT_ENCODER_PORT_B);

		// initializing drivetrain
		drivetrain = new Drivetrain(right, left, leftEncoder, rightEncoder, new AnalogGyro(RobotMap.GYRO_PORT));

		oi = new OI();

		dbc = new DashBoardController();
		acc = new BuiltInAccelerometer();

		location = new RobotLocationAccelerometers(Constants.ROBOT_INIT_LOCATION, Timer::getFPGATimestamp, acc::getX,
				acc::getY);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		dbc.update();
		location.update();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
