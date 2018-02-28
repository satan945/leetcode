/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem
 * 
 * https://leetcode.com/problems/the-skyline-problem/description/
 * 
 * @author abel created on 2018/2/26 下午6:45
 * @version $Id$
 */
public class TheSkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            heights.add(new int[] { building[0], -building[2] });
            heights.add(new int[] { building[1], building[2] });
        }
        heights.sort((o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.offer(0);
        int prev = 0;
        for (int[] h : heights) {
            if (h[1] < 0) {
                queue.offer(-h[1]);
            } else {
                queue.remove(h[1]);
            }
            int cur = queue.peek();
            if (cur != prev) {
                res.add(new int[] { h[0], cur });
                prev = cur;
            }
        }
        return res;
    }
}
