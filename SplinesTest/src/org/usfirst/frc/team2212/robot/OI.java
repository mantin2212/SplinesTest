package org.usfirst.frc.team2212.robot;

import org.usfirst.frc.team2212.robot.commands.DriveTankByRoute;
import org.usfirst.frc.team2212.robot.utils.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI/* gevald */ {

	private Joystick driver = new Joystick(0);

	private JoystickButton followRoute;

	public OI() {
		followRoute = new JoystickButton(driver, 1);

		followRoute.whenPressed(new DriveTankByRoute(Constants.ROUTES_TARGET, 5));
	}

}
