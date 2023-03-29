// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ConeVisionSubsystem;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

/** An example command that uses an example subsystem. */
public class CubeVisionCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ConeVisionSubsystem m_subsystem;

    public PhotonCamera photonCamera;
    private boolean targetFound = false;

    private Pose2d goalPose = null;
    private PhotonTrackedTarget lastTarget;
    int count;
    private static final Transform2d TAG_TO_GOAL =
            new Transform2d(new Translation2d(0, 0), Rotation2d.fromDegrees(180.0));

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public CubeVisionCommand(ConeVisionSubsystem coneVisionSubsystem) {
        m_subsystem = coneVisionSubsystem;
        SmartDashboard.putNumber("CubePipeLine Constructor", 0);
        // Use addRequirements() here to declare subsystem dependencies.

        addRequirements(coneVisionSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        this.m_subsystem.setPipeLineIndex(Constants.VisionConstants.CUBE_PIPELINE_INDEX);
        ;

        SmartDashboard.putNumber("CubePipeLine INIT setPipeLineIndex ", Constants.VisionConstants.CUBE_PIPELINE_INDEX);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        SmartDashboard.putNumber("CubePipeLine Executing Vision", count++);

        if (m_subsystem.hasTargets()) {
            SmartDashboard.putBoolean("CubePipeLine TargetFound", m_subsystem.hasTargets());

            SmartDashboard.putNumber("CubePipeLine Target Yaw", m_subsystem.getSkewVal());
            SmartDashboard.putNumber("CubePipeLine Target Area", m_subsystem.getAreVal());
            SmartDashboard.putNumber("CubePipeLine Target Pitch", m_subsystem.getPitchVal());

            this.targetFound = true;

        } else {
            SmartDashboard.putNumber("CubePipeLine No targets found Appa", 1);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.targetFound;
    }
}
