package io.github.geniusay.solution;

public enum SolutionStepEnum {
    GOD(1),
    SECOND(2),
    THIRD(3),
    FOUR(4);

    final int stepCode;
    SolutionStepEnum(int stepCode){
        this.stepCode = stepCode;
    }

    public int getStepCode() {
        return stepCode;
    }
}
