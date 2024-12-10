package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Subsystems.Intake;

public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX masterTalonFx;
    private final TalonFX slaveTalonFX;

    private final PIDController pidController;

    public IntakeSubsystem() {
        this.masterTalonFx = new TalonFX(Intake.INTAKE_MOTOR_CAN_ID);
        this.pidController = new PIDController(0, 0, 0);
        this.slaveTalonFX = new TalonFX(0);

        this.slaveTalonFX.setControl(new Follower(Intake.INTAKE_MOTOR_CAN_ID, true));
    }

    public void setPercentage(double percentage) {
        this.masterTalonFx.set(percentage);
    }

    public void setVelocity(double rpm) {
        pidController.setSetpoint(rpm);
    }

    public double calculateInVoltage(double rpm) {
        return pidController.calculate(rpm);
    }

    public void setVoltage(double voltage) {
        this.masterTalonFx.setVoltage(voltage);
    }

    public double getVelocityInRPM() {
        return this.masterTalonFx.getVelocity().getValueAsDouble() * 60;
    }

    public void executePID() {
        setVoltage(calculateInVoltage(getVelocityInRPM()));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake speed in percentage", this.masterTalonFx.get());
        SmartDashboard.putNumber("Intake speed in RPM",
                masterTalonFx.getVelocity().getValueAsDouble() * 60);
    }
}
