/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import org.ccs.leetcode.bean.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms
 * 
 * https://leetcode.com/problems/meeting-rooms/description/
 * 
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine
 * if a person could attend all meetings.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/24 下午1:43
 * @version $Id$
 */
public class MeetingRoom {

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.end));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1].end > intervals[i].start) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[] { new Interval(7, 10), new Interval(2, 4) };
        MeetingRoom meetingRoom = new MeetingRoom();
        System.out.println(meetingRoom.canAttendMeetings(intervals));
    }
}
