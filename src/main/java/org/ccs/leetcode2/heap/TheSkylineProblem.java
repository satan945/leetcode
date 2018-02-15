/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem
 * <p>
 * https://leetcode.com/problems/the-skyline-problem
 * </p>
 * 
 * @author Abel created on 2018/2/14 17:56
 * @version $Id$
 */
public class TheSkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            heights.add(new int[] { building[0], -building[2] });
            heights.add(new int[] { building[1], building[2] });
        }
        heights.sort((o1, o2) -> (o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]));
        PriorityQueue<Integer> hQueue = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        hQueue.offer(0);
        int prev = 0;
        List<int[]> res = new ArrayList<>();
        for (int[] h : heights) {
            if (h[1] < 0) {
                hQueue.offer(-h[1]);
            } else {
                hQueue.remove(h[1]);
            }
            if (hQueue.peek() != prev) {
                res.add(new int[] { h[0], hQueue.peek() });
                prev = hQueue.peek();
            }
        }
        return res;
    }

    class BuildingPoint {
        public int x;
        public int y;
        public int type;

        public BuildingPoint(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public List<int[]> getSkyline2(int[][] buildings) {
        List<BuildingPoint> buildingPoints = new ArrayList<>();
        for (int[] building : buildings) {
            buildingPoints.add(new BuildingPoint(building[0], building[2], 0));
            buildingPoints.add(new BuildingPoint(building[1], building[2], 1));
        }
        buildingPoints.sort((o1, o2) -> {// bug
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            } else if (o1.y != o2.y) {
                if (o1.type != o2.type) {
                    return o2.y - o1.y;
                } else if (o1.type == 0) {
                    return o2.y - o1.y;
                } else {
                    return o1.y - o2.y;
                }
            } else {
                return o1.type - o2.type;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq.offer(0);
        int prev = 0;
        List<int[]> res = new ArrayList<>();
        for (BuildingPoint point : buildingPoints) {
            if (point.type == 0) {
                pq.offer(point.y);
            } else {
                pq.remove(point.y);
            }
            if (pq.peek() != prev) {
                res.add(new int[] { point.x, pq.peek() });
                prev = pq.peek();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TheSkylineProblem solution = new TheSkylineProblem();
        List<int[]> res = solution.getSkyline2(new int[][] { { 0, 2, 3 }, { 2, 5, 3 } });
        List<int[]> res1 = solution.getSkyline2(new int[][] { { 1, 2, 1 }, { 1, 2, 2 }, { 1, 2, 3 } });
        System.out.println(res1);
    }
}
