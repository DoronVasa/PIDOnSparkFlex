package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterRPMCommand extends Command {

    private final ShooterSubsystem shooterSubsystem;
    private double setpointInRPM;

    public ShooterRPMCommand(ShooterSubsystem shooterSubsystem, double setpointInRPM) {
        this.shooterSubsystem = shooterSubsystem;
        this.setpointInRPM = setpointInRPM;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setSetpoint(this.setpointInRPM);
    }
}
