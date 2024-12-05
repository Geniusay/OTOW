package io.github.geniusay.solution;

import io.github.geniusay.common.params.ParamsProvider;

import java.util.*;

/**
 * 方案导游，用来说明当前方案是属于哪一个步骤
 */
public interface SolutionGuide extends ParamsProvider {

    // 属于哪一个方案步骤
    Class<? extends SolutionStep> belongToSolutionStep();

    // 父级方案
    List<Class<? extends Solution>> fatherSolutions();
}
