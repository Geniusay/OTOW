package io.github.geniusay.solution.impl.java;

import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import io.github.geniusay.template.meta.MetaMethod;

import java.util.List;

public class ControllerSolution extends Solution {

    private String name;

    private final String controllerPackage = "controller";

    private List<MetaMethod> metaMethods;

    public ControllerSolution() {
    }

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return null;
    }

    @Override
    public Object execute() {
        return null;
    }
}
