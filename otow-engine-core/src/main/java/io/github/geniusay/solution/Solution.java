package io.github.geniusay.solution;

import io.github.geniusay.common.params.ParamsProvider;

public abstract class Solution implements SolutionExecutor, ParamsProvider {

    // 方案等级
    protected int grade;

    protected SolutionRestraint restraint;
}
