package io.github.geniusay.solution;

import org.springframework.stereotype.Component;

import static io.github.geniusay.solution.SolutionStepEnum.FOUR;

@Component
public class CodingSolutionStep extends SolutionStep{

    public CodingSolutionStep() {
        super(FOUR, "coding");
        this.fatherSteps.add(CodingSolutionStep.class);
    }
}
