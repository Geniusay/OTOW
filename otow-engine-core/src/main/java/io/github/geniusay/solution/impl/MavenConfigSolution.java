package io.github.geniusay.solution.impl;

import io.github.geniusay.solution.ConfigSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;

import java.util.List;

public class MavenConfigSolution extends Solution {

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return List.of(JavaProjectStructureSolution.class);
    }

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return ConfigSolutionStep.class;
    }
}
