/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.topo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 210. Course Schedule II
 * 
 * https://leetcode.com/problems/course-schedule-ii
 * 
 * @author abel created on 2018/2/27 下午3:35
 * @version $Id$
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }
        int[] degree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] pre = prerequisites[i];
            degree[pre[1]]++;
            graph[pre[0]].add(pre[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        if (queue.isEmpty()) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        int cur = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[cur++] = course;
            for (int i = 0; i < graph[course].size(); i++) {
                int pointer = (int) graph[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    count++;
                    queue.offer(pointer);
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }

    public static void main(String[] args) {
        CourseScheduleII courseScheduleII = new CourseScheduleII();
        courseScheduleII.findOrder(2, new int[][] { { 0, 1 }, { 1, 0 } });
    }
}
