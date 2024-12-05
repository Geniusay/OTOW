package io.github.geniusay.solution;

import java.util.ArrayList;
import java.util.List;

public abstract class SolutionStep {

    // 当前阶段
    protected SolutionStepEnum step;

    // 当前方案名称
    protected String stepName;

    // 下一步方案
    protected SolutionStep nextStep;

    // 上一步方案步骤
    protected final List<Class<? extends SolutionStep>> fatherSteps;

    // 当前方案步骤储存的方案
    protected final List<Solution> solutionContainers;

    public SolutionStep(SolutionStepEnum step, String stepName) {
        this.step = step;
        this.stepName = stepName;
        this.solutionContainers = new ArrayList<>();
        this.fatherSteps = new ArrayList<>();
    }

    public SolutionStepEnum getStep() {
        return step;
    }

    public int getStepCode() {
        return step.getStepCode();
    }

    public String getStepName() {
        return stepName;
    }

    public List<Solution> getSolutionContainers() {
        return solutionContainers;
    }
}
