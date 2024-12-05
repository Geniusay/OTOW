package io.github.geniusay.solution;

public class ProjectStructureSolutionStep extends SolutionStep{

    public ProjectStructureSolutionStep() {
        super(SolutionStepEnum.SECOND, "projectStructure");
        this.fatherSteps.add(GodSolutionStep.class);
    }
}
