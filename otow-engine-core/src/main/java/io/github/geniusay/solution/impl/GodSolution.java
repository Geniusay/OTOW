package io.github.geniusay.solution.impl;

import io.github.geniusay.solution.GodSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import org.springframework.stereotype.Component;

@Component
public class GodSolution extends Solution {

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return GodSolutionStep.class;
    }
}
