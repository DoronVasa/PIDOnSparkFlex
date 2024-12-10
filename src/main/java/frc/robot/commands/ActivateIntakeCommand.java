package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ActivateIntakeCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;

    public ActivateIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        this.intakeSubsystem.setVelocity(3000);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        this.intakeSubsystem.executePID();
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setPercentage(0);
    }
}
