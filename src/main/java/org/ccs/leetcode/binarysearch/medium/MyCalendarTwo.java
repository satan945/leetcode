/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.binarysearch.medium;

import java.util.TreeMap;

/**
 * 731. My Calendar II
 * <p>
 * https://leetcode.com/problems/my-calendar-ii
 * <p>
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a
 * triple booking.
 * 
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 * 
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common
 * to all 3 events.)
 * 
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 * 
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * </p>
 * todo
 * 
 * @author abel created on 2017/12/1 下午6:53
 * @version $Id$
 */
public class MyCalendarTwo {

    private TreeMap<Integer, Integer> treeMap;

    public MyCalendarTwo() {
        treeMap = new TreeMap<>();

    }

    public boolean book(int start, int end) {

        return false;
    }
}
