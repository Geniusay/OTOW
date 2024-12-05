package io.github.geniusay.solution;

import org.springframework.stereotype.Component;

@Component
public class ConfigSolutionStep extends SolutionStep{

    public ConfigSolutionStep() {
        super(SolutionStepEnum.THIRD, "config");
        this.fatherSteps.add(ProjectStructureSolutionStep.class);
    }
}
