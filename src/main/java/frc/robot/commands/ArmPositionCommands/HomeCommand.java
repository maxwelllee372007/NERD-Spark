package frc.robot.commands.ArmPositionCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commands.ArmMoveCommands.MoveArmCommand;
import frc.robot.commands.ArmMoveCommands.MoveElevatorCommand;
import frc.robot.commands.ArmMoveCommands.MoveGripperCommand;
import frc.robot.commands.ArmMoveCommands.MoveWristCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class HomeCommand extends SequentialCommandGroup {
    public HomeCommand(
            ArmSubsystem armSubsystem,
            ElevatorSubsystem elevatorSubsystem,
            WristSubsystem wristSubsystem,
            GripperSubsystem gripperSubsystem) {
        addCommands(
                new InstantCommand(() -> armSubsystem.setArmPositionState(ArmSubsystem.ArmPosition.HOME)),
                new ParallelCommandGroup(
                        new MoveWristCommand(wristSubsystem, ArmConstants.homePosition.wristCmdPos()),
                        new MoveElevatorCommand(elevatorSubsystem, ArmConstants.homePosition.inclinatorCmdPos()),
                        new MoveGripperCommand(gripperSubsystem, armSubsystem, MoveGripperCommand.GripperState.CLOSED)),
                new MoveArmCommand(
                        armSubsystem,
                        ArmConstants.homePosition.armCmdPos(),
                        ArmConstants.homePosition.smartMotionMaxVel(),
                        ArmConstants.homePosition.smartMotionMaxAccel()));
    }
}
