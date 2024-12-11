package io.github.geniusay.solution.impl.java;

import io.github.geniusay.solution.ProjectStructureSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import io.github.geniusay.solution.impl.GodSolution;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavaProjectStructureSolution extends Solution {

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return List.of(GodSolution.class);
    }

    @Override
    public String solutionName() {
        return null;
    }

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return ProjectStructureSolutionStep.class;
    }

    @Override
    public Object execute() {
        return null;
    }
}
