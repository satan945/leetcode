/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest66;

import org.ccs.leetcode.bean.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 759. Employee Free Time
 * 
 * @author abel created on 2018/1/30 下午4:44
 * @version $Id$
 */
public class EmployeeFreeTime {

    /**
     * 759. Employee Free Time
     * <p>
     * We are given a list avail of employees, which represents the free time for each employee.
     *
     * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
     *
     * Return the list of finite intervals representing common, positive-length free time for all employees, also in
     * sorted order.
     * </p>
     *
     * @param avails
     * @return
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> (o1.start == o2.start) ? o2.end - o1.end : o1.start - o2.start);
        avails.forEach(priorityQueue::addAll);
        Interval tmp = priorityQueue.poll();
        while (!priorityQueue.isEmpty()) {
            if (tmp.end < priorityQueue.peek().start) {
                res.add(new Interval(tmp.end, priorityQueue.peek().start));
                tmp = priorityQueue.poll();
            } else {
                tmp = tmp.end < priorityQueue.peek().end ? priorityQueue.peek() : tmp;
                priorityQueue.poll();
            }
        }
        return res;
    }
}
