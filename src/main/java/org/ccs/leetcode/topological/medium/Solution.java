/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.topological.medium;

/**
 * @author abel created on 2017/11/1 下午5:18
 * @version $Id$
 */
public class Solution {

    /**
     * 207. Course Schedule
     * <p>
     * https://leetcode.com/problems/course-schedule
     * <p>
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     * 
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
     * expressed as a pair: [0,1]
     * 
     * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
     * 
     * For example:
     * 
     * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is
     * possible.
     * 
     * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and
     * to take course 0 you should also have finished course 1. So it is impossible.
     * </p>
     * 
     * @param numCourses
     * @param prerequisites
     * @return todo
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    /**
     * 210. Course Schedule II
     * <p>
     * https://leetcode.com/problems/course-schedule-ii
     * <p>
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     * 
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
     * expressed as a pair: [0,1]
     * 
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
     * take to finish all courses.
     * 
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
     * courses, return an empty array.
     * 
     * For example:
     * 
     * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the
     * correct course order is [0,1]
     * 
     * 4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take course 3 you should have finished
     * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course
     * order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
     * </p>
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

    }
}
