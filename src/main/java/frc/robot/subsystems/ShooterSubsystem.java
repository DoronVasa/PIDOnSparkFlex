package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    final CANSparkFlex leftSparkFlex;
    final CANSparkFlex rightSparkFlex;
    final AbsoluteEncoder leftEncoder;
    final AbsoluteEncoder rightEncoder;
    final SparkPIDController masterPIDController;

    public ShooterSubsystem() {
        leftSparkFlex = new CANSparkFlex(0, MotorType.kBrushless);
        rightSparkFlex = new CANSparkFlex(0, MotorType.kBrushless);

        leftSparkFlex.restoreFactoryDefaults();
        rightSparkFlex.restoreFactoryDefaults();

        leftSparkFlex.follow(rightSparkFlex);
        leftSparkFlex.setInverted(true);

        this.rightEncoder = rightSparkFlex.getAbsoluteEncoder();
        this.leftEncoder = leftSparkFlex.getAbsoluteEncoder();

        this.leftEncoder.setInverted(true);

        this.leftEncoder.setVelocityConversionFactor(60);
        this.rightEncoder.setVelocityConversionFactor(60);

        this.masterPIDController = this.leftSparkFlex.getPIDController();
        this.masterPIDController.setP(0);
        this.masterPIDController.setI(0);
        this.masterPIDController.setD(0);
        this.masterPIDController.setFF(0);

    }

    public void setSetpoint(double setpoint) {
        this.masterPIDController.setReference(setpoint, ControlType.kVelocity, 0);
    }

    public void setPercentage(double percentage) {
        this.rightSparkFlex.set(percentage);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("shooter RPM", (leftEncoder.getVelocity() + rightEncoder.getVelocity()) / 2);
    }
}
