package io.github.geniusay.solution;

import java.util.ArrayList;
import java.util.List;

public abstract class Solution implements SolutionExecutor, SolutionGuide{

    private final List<Object> fileReferences;

    private final String solutionName;

    public Solution(String solutionName) {
        this.solutionName = solutionName;
        this.fileReferences = new ArrayList<>();
    }

    public Solution() {
        this.solutionName = this.getClass().getSimpleName();
        this.fileReferences = new ArrayList<>();
    }

    @Override
    public List<Class<? extends Solution>> fatherSolutions() {
        return new ArrayList<>();
    }

    public String getSolutionName() {
        return solutionName;
    }

    public List<Object> getFileReferences() {
        return fileReferences;
    }

    public void addFileReference(List<Object> fileReferences){
        this.fileReferences.addAll(fileReferences);
    }

    public void addFileReference(Object...fileReferences){
        this.fileReferences.addAll(List.of(fileReferences));
    }


}
