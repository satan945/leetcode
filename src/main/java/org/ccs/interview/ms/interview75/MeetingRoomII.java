/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import org.ccs.leetcode.bean.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 * 
 * https://leetcode.com/problems/meeting-rooms-ii
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 * </p>
 * 
 * @author abel created on 2018/2/24 下午1:51
 * @version $Id$
 */
public class MeetingRoomII {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (Comparator.comparingInt(o -> o.start)));
        PriorityQueue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
        queue.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = queue.poll();
            if (interval.end <= intervals[i].start) {
                interval.end = intervals[i].end;
            } else {
                queue.offer(intervals[i]);
            }
            queue.offer(interval);
        }
        return queue.size();

    }

    public int minMeetingRooms2(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int cnt = 0, j = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[j]) {
                cnt++;
            } else {
                j++;
            }
        }
        return cnt;

    }

    public static void main(String[] args) {

    }
}
