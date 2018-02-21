/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.binarysearch.medium;

import org.ccs.leetcode.bean.Interval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 436. Find Right Interval
 * <p>
 * https://leetcode.com/problems/find-right-interval
 * </p>
 * <p>
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger
 * than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 * 
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum
 * start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the
 * interval i. Finally, you need output the stored value of each interval as an array.
 * </p>
 * 
 * @author abel created on 2018/2/14 下午9:58
 * @version $Id$
 */
public class FindRightInterval {

    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return new int[] { -1 };
        }
        int[] res = new int[intervals.length];
        int[] start = new int[intervals.length];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            indexMap.put(intervals[i].start, i);
            start[i] = intervals[i].start;
        }
        Arrays.sort(start);
        for (int i = 0; i < intervals.length; i++) {
            int right = biSearchStartByEnd(intervals[i].end, start);
            if (right >= intervals[i].end) {
                res[i] = indexMap.get(right);
            } else {
                res[i] = -1;
            }
        }
        return res;
    }

    // find the interval with the min start greater or equal to target
    private int biSearchStartByEnd(int target, int[] starts) {
        int l = 0, r = starts.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (starts[m] == target) {
                return starts[m];
            }
            if (starts[m] < target) {
                l = m + 1;
            } else {
                r = m ;
            }
        }
        return starts[l];
    }

    public static void main(String[] args) {
        FindRightInterval findRightInterval = new FindRightInterval();
        // Interval[] nums = new Interval[] { new Interval(3, 4), new Interval(2, 3), new Interval(1, 2) };
        // Interval[] nums = new Interval[] { new Interval(1, 4), new Interval(2, 3), new Interval(3, 4) };
        Interval[] nums = new Interval[] { new Interval(1, 12), new Interval(2, 9), new Interval(3, 10),
                new Interval(13, 14), new Interval(15, 16), new Interval(16, 17) };
        int[] res = findRightInterval.findRightInterval(nums);
        // int[] nums2 = new int[] { 1, 2, 3, 13, 15, 16, 17 };
        // System.out.println(findRightInterval.biSearchStartByEnd(14, nums2));
        System.out.println(res);
    }
}
