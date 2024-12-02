package io.github.geniusay.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 方案生成树
 */
public class SolutionTree {

    private SolutionNode root;

    public static class SolutionNode{
        private final Solution solution;

        private final List<SolutionNode> childNodes = new ArrayList<>();

        public SolutionNode(Solution solution) {
            this.solution = solution;
        }

        public Solution getSolution() {
            return solution;
        }

        public void addChild(SolutionNode node){
            childNodes.add(node);
        }

        public void addChild(SolutionNode...node){
            childNodes.addAll(Stream.of(node).collect(Collectors.toList()));
        }

        public List<SolutionNode> childNodes(){
            return this.childNodes;
        }
    }
}
