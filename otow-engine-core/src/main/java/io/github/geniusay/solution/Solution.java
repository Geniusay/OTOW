package io.github.geniusay.solution;

import java.util.ArrayList;
import java.util.List;

public abstract class Solution implements SolutionExecutor, SolutionGuide{

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return new ArrayList<>();
    }

    public abstract String solutionName();
}
