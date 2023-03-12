package frc.robot.commands;

import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class MoveGripperCommand extends CommandBase {
  private final GripperSubsystem m_subsystem;
  private final ArmSubsystem armSubsystem;

  public enum GripperState {
    Open,
    Closed,
  }

  private double leftPosition;
  private double rightPosition;
  private GripperState state;


  /**
   * Creates a new MoveGripperCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MoveGripperCommand(GripperSubsystem subsystem, ArmSubsystem armSubsystem, GripperState state) {
    m_subsystem = subsystem;
    this.armSubsystem = armSubsystem;
    this.state = state;

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    String leftKey = "";
    String rightKey = "";

    if (state == GripperState.Open) {
      leftKey = "leftGripperOpenCmdPos";
      rightKey = "rightGripperOpenCmdPos";
    } else {
      leftKey = "leftGripperCloseCmdPos";
      rightKey = "rightGripperCloseCmdPos";
    }
    
    switch (armSubsystem.getArmPositionState()) {
      case Home:
          leftPosition = ArmConstants.homePosition.get(leftKey);
          rightPosition = ArmConstants.homePosition.get(rightKey);
      case BucketPickup:
          leftPosition = ArmConstants.intakeBucketPosition.get(leftKey);
          rightPosition = ArmConstants.intakeBucketPosition.get(rightKey);
      case GroundPickup:
          leftPosition = ArmConstants.intakeGroundPosition.get(leftKey);
          rightPosition = ArmConstants.intakeGroundPosition.get(rightKey);
      case ShelfPickup:
          leftPosition = ArmConstants.intakeShelfPosition.get(leftKey);
          rightPosition = ArmConstants.intakeShelfPosition.get(rightKey);
      case HighDrop:
          leftPosition = ArmConstants.scoreHighPosition.get(leftKey);
          rightPosition = ArmConstants.scoreHighPosition.get(rightKey);
      case MidDrop:
          leftPosition = ArmConstants.scoreMidPosition.get(leftKey);
          rightPosition = ArmConstants.scoreMidPosition.get(rightKey);
      case GroundDrop:
          leftPosition = ArmConstants.scoreGroundPosition.get(leftKey);
          rightPosition = ArmConstants.scoreGroundPosition.get(rightKey);
      default:
        break;
    }

    m_subsystem.setLeftPosition(leftPosition);
    m_subsystem.setRightPosition(rightPosition);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // todo
    return false;
  }
}
