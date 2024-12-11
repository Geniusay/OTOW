package io.github.geniusay.solution.impl.java;

import io.github.geniusay.solution.CodingSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;

import static io.github.geniusay.common.SolutionConstant.JAVA_MODULE_SOLUTION;

public class JavaModuleSolution extends Solution{

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return CodingSolutionStep.class;
    }

    @Override
    public Object execute() {
        return null;
    }

    @Override
    public String solutionName() {
        return JAVA_MODULE_SOLUTION;
    }
}
