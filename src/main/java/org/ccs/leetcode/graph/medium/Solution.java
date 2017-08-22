/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.graph.medium;

import org.ccs.leetcode.bean.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author abel created on 2017/8/17 下午11:45
 * @version $Id$
 */
public class Solution {

    /**
     * 133. Clone Graph
     * <p>
     * https://leetcode.com/problems/clone-graph
     * <p>
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     * 
     * 
     * OJ's undirected graph serialization: Nodes are labeled uniquely.
     * 
     * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node. As an
     * example, consider the serialized graph {0,1,2#1,2#2,2}.
     * 
     * The graph has a total of three nodes, and therefore contains three parts as separated by #.
     * 
     * First node is labeled as 0. Connect node 0 to both nodes 1 and 2. Second node is labeled as 1. Connect node 1 to
     * node 2. Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle. Visually, the
     * graph looks like the following:
     * 
     * </p>
     * 
     * @param node
     * @return
     */
    private HashMap<Integer, UndirectedGraphNode> mapping = new HashMap<>();

    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        if (mapping.containsKey(node.label)) {
            return mapping.get(node.label);
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        mapping.put(clone.label, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }

    public UndirectedGraphNode cloneGraphUBFS(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
        HashMap<Integer, UndirectedGraphNode> mapping = new HashMap<>();
        mapping.put(copyNode.label, copyNode);
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.pop();
            for (UndirectedGraphNode neighbor : curNode.neighbors) {
                if (!mapping.containsKey(neighbor.label)) {
                    mapping.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                mapping.get(curNode.label).neighbors.add(mapping.get(neighbor.label));
            }
        }
        return copyNode;
    }
}
