package io.github.geniusay.solution.impl;

import io.github.geniusay.solution.CodingSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;

public class JavaModuleSolution extends Solution{

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return CodingSolutionStep.class;
    }
}
