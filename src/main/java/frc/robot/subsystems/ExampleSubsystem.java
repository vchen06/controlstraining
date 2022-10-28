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
  
  private final CANSparkMax motor = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(16, MotorType.kBrushless);

  private final WPI_TalonSRX talon = new WPI_TalonSRX(8);

  private final XboxController controller = new XboxController(0);

  private final Timer timer = new Timer();

  private final AnalogPotentiometer sensor1 = new AnalogPotentiometer(0);
  private final AnalogPotentiometer sensor2 = new AnalogPotentiometer(1);
  private final AnalogPotentiometer sensor3 = new AnalogPotentiometer(2);

  public ExampleSubsystem() {
    motor.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor2.follow(motor);
    motor.setIdleMode(IdleMode.kCoast);
    motor2.setIdleMode(IdleMode.kCoast);

  }

  double speed = 0;
  boolean run = false;
  boolean stored = false;
  boolean button = false;

  @Override
  public void periodic() {
    talon.set(0);

    //storing balls
    if (run && sensor3.get() >= 1.65) { //ball reaches top
      run = false;
      stored = true;
      speed = 0;
    } else if (sensor1.get() >= 1.65 || sensor2.get() >= 1.65) { 
    //if ball at entrance or lower part of store, run motors
      run = true;
      speed = 1;
    }

    //shooting balls
    if (stored) {
      if (button) {
        motor.set(1); 
        //when ball is shot, stop doing motor
        if (timer.get() >= 1) { //change value
          button = false;
          stored = false;
          timer.stop();
          timer.reset();
          motor.set(0);
        }
      }
      else if (controller.getAButtonPressed()) {
        button = true;
        timer.start();
      } 
    }



    
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
