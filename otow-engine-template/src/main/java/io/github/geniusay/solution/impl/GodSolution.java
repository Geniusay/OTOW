package io.github.geniusay.solution.impl;

import io.github.geniusay.solution.GodSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import org.springframework.stereotype.Component;

import static io.github.geniusay.common.SolutionConstant.GOD_SOLUTION;

@Component
public class GodSolution extends Solution {

    private String root;

    private String projectName;

    private String user;

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return GodSolutionStep.class;
    }

    @Override
    public Object execute() {
        return null;
    }

    @Override
    public String solutionName() {
        return GOD_SOLUTION;
    }
}
