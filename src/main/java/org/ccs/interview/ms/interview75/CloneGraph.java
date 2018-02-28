/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import org.ccs.leetcode.bean.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 133. Clone Graph
 * 
 * @author abel created on 2018/2/22 下午4:12
 * @version $Id$
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> cloneMap = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        cloneMap.put(node.label, new UndirectedGraphNode(node.label));
        queue.offer(node);
        UndirectedGraphNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            List<UndirectedGraphNode> neighbors = cur.neighbors;
            for (UndirectedGraphNode neighbor : neighbors) {
                if (!cloneMap.containsKey(neighbor.label)) {
                    cloneMap.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    queue.offer(neighbor);
                }
                cloneMap.get(cur.label).neighbors.add(cloneMap.get(neighbor.label));
            }
        }
        return cloneMap.get(node.label);
    }

    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> cloneMap = new HashMap<>();
        return clone(node, cloneMap);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> cloneMap) {
        if (node == null) {
            return null;
        }
        if (cloneMap.containsKey(node.label)) {
            return cloneMap.get(node.label);
        }
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        cloneMap.put(node.label, copy);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, cloneMap));
        }
        return copy;
    }
}
