/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.sort.easy;

import org.ccs.leetcode.bean.Interval;

import java.util.Arrays;

/**
 * @author abel created on 2017/8/11 下午2:31
 * @version $Id$
 */
public class Solution {

    /**
     * 252. Meeting Rooms
     * <p>
     * https://leetcode.com/problems/meeting-rooms
     * <p>
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.
     * 
     * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
     * </p>
     * 
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length <= 1) {
            return true;
        }
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
        int end = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = intervals[i];
            if (end > interval.start) {
                return false;
            } else {
                end = interval.end;
            }
        }
        return true;
    }


}
