package io.github.geniusay.solution.impl.java;

import com.alibaba.fastjson.JSONObject;
import io.github.geniusay.solution.ConfigSolutionStep;
import io.github.geniusay.solution.Solution;
import io.github.geniusay.solution.SolutionStep;
import io.github.geniusay.template.java.ApplicationConfigTemplate;

import java.util.List;

import static io.github.geniusay.common.SolutionConstant.SPRING_CONFIG_SOLUTION;

public class SpringBootConfigSolution extends Solution {

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return List.of(JavaProjectStructureSolution.class);
    }

    @Override
    public String solutionName() {
        return SPRING_CONFIG_SOLUTION;
    }

    @Override
    public Class<? extends SolutionStep> belongToSolutionStep() {
        return ConfigSolutionStep.class;
    }

    @Override
    public Object execute() {
        return new ApplicationConfigTemplate(null);
    }
}
