package io.github.geniusay.solution.impl.java;

import io.github.geniusay.solution.ConfigSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;

import java.util.List;

import static io.github.geniusay.common.SolutionConstant.MAVEN_CONFIG_SOLUTION;

public class MavenConfigSolution extends Solution {

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return List.of(JavaProjectStructureSolution.class);
    }

    @Override
    public String solutionName() {
        return MAVEN_CONFIG_SOLUTION;
    }

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return ConfigSolutionStep.class;
    }

    @Override
    public Object execute() {
        return null;
    }
}
