/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. Course Schedule
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is
 * possible.
 * 
 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to
 * take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how
 * a graph is represented. You may assume that there are no duplicate edges in the input prerequisites.
 * 
 * Hints:
 *
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
 * ordering exists and therefore it will be impossible to take all courses.
 *
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort.
 * 
 * Topological sort could also be done via BFS.
 * 
 * </p>
 * 
 * @author Abel created on 2018/1/5 18:08
 * @version $Id$
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) {
            return true;
        }
        int count = numCourses;
        int[] degrees = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            degrees[edge[0]]++;
            graph.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
                count--;
            }
        }
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            List<Integer> edges = graph.get(course);
            for (int edge : edges) {
                degrees[edge]--;
                if (degrees[edge] == 0) {
                    queue.offer(edge);
                    count--;
                }
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();
        System.out.println(solution.canFinish(2, new int[][] { { 0, 1 } }));
    }
}
