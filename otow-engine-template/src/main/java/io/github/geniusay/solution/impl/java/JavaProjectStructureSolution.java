package io.github.geniusay.solution.impl.java;

import io.github.geniusay.solution.ProjectStructureSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import io.github.geniusay.solution.impl.GodSolution;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.github.geniusay.common.SolutionConstant.JAVA_PROJECT_SOLUTION;

@Component
public class JavaProjectStructureSolution extends Solution {

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return List.of(GodSolution.class);
    }

    @Override
    public String solutionName() {
        return JAVA_PROJECT_SOLUTION;
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
