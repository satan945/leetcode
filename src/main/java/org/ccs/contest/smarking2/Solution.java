/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.smarking2;

import java.util.Arrays;

import org.ccs.leetcode.bean.Interval;

/**
 * @author abel created on 2017/12/18 下午10:45
 * @version $Id$
 */
public class Solution {

    /**
     * 435. Non-overlapping Intervals
     * 
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1.start != o2.start ? o1.start - o2.start : o2.end - o1.end);
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Interval[] intervals = new Interval[] { new Interval(1, 2), new Interval(3, 4), new Interval(1, 3) };
        solution.eraseOverlapIntervals(intervals);

    }

}
