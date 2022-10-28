// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private final CANSparkMax flywheel = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax flywheel2 = new CANSparkMax(16, MotorType.kBrushless);

  private final WPI_TalonSRX talon = new WPI_TalonSRX(8);

  private final XboxController controller = new XboxController(0);

  private final Timer timer = new Timer();

  private final AnalogPotentiometer sensor1 = new AnalogPotentiometer(0);
  private final AnalogPotentiometer sensor2 = new AnalogPotentiometer(1);
  private final AnalogPotentiometer sensor3 = new AnalogPotentiometer(2);

  private final double ballDetected = 1.0; 

  public ExampleSubsystem() {
    flywheel.restoreFactoryDefaults();
    flywheel2.restoreFactoryDefaults();
    flywheel2.follow(flywheel);
    flywheel.setIdleMode(IdleMode.kCoast);
    flywheel2.setIdleMode(IdleMode.kCoast);

  }

  double speed = 1; //change
  boolean run = false;
  boolean stored = false;
  boolean buttonPressed = false;
  double launchSpeed = 1; //change

  @Override
  public void periodic() {

    //storing balls
    if (run && sensor3.get() >= ballDetected) { //ball reaches top
      run = false;
      stored = true;
      talon.set(0);
    } else if (!stored && (sensor1.get() >= ballDetected || sensor2.get() >= ballDetected)) { 
    //if ball at entrance or lower part of store, run motors
      run = true;
      talon.set(speed);
    }

    //shooting balls
    if (stored) {
      if (buttonPressed) { 
        if (timer.get() >= 1) { //time for ball to be shot, change value
          buttonPressed = false;
          stored = false;
          timer.stop();
          timer.reset();
          flywheel.set(0);
        }
      }
      else if (controller.getAButtonPressed()) {
        buttonPressed = true;
        flywheel.set(launchSpeed);
        timer.start();
      } 
    }



    
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
