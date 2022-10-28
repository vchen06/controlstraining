// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final CANSparkMax motor = new CANSparkMax(17, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(1, MotorType.kBrushless);
  //flywheels
  private final CANSparkMax motor3 = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax motor4 = new CANSparkMax(16, MotorType.kBrushless);


  private final XboxController controller = new XboxController(0);

  private final Timer timer = new Timer();

  private final AnalogPotentiometer analog1 = new AnalogPotentiometer(0);
  private final AnalogPotentiometer analog2 = new AnalogPotentiometer(1);
  private final AnalogPotentiometer analog3 = new AnalogPotentiometer(2);

  public ExampleSubsystem() {
    motor.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor2.follow(motor);
    motor.setIdleMode(IdleMode.kCoast);
    motor2.setIdleMode(IdleMode.kCoast);

    timer.start();
  }

  double speed = 0;
  
  
  boolean run = false;

  boolean stored = false;

  @Override
  public void periodic() {
    System.out.println(analog1.get());
    System.out.println(analog2.get());
    System.out.println(analog3.get());
    System.out.println("_");
    // if (analog1.get() >= n) {
    //   motor.set(1);
    // } 




    // System.out.println(analog.get());
    // motor.set(1-analog.get()/0.64);

    //TIMER
    // if (timer.get()<4) {
    //   speed = timer.get()*0.25;
    //   motor.set(speed);
    // } else if (timer.get()<6){
    //   speed = 1;
    //   motor.set(speed);
    // } else if (timer.get()<10) {
    //   speed = 1-timer.get()/4;
    //   motor.set(speed);
    // }
    
    //day 1
    // if (controller.getAButtonPressed()) {
    //   run = !run;
    // }
    // if (!run) {
    //   motor.set(0);
    // } else {
    //   motor.set(controller.getRightTriggerAxis() - controller.getLeftTriggerAxis());
    // }

    
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
