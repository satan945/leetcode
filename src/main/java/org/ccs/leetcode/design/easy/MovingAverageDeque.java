/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.easy;

/**
 * @author abel created on 2017/9/22 下午5:59
 * @version $Id$
 */

import java.util.ArrayDeque;

/**
 * 346. Moving Average from Data Stream
 * <p>
 * https://leetcode.com/problems/moving-average-from-data-stream
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * 
 * For example, MovingAverage m = new MovingAverage(3); m.next(1) = 1 m.next(10) = (1 + 10) / 2 m.next(3) = (1 + 10 + 3)
 * / 3 m.next(5) = (10 + 3 + 5) / 3
 * </p>
 */
public class MovingAverageDeque {
    private ArrayDeque<Integer> deque;
    private int sum;
    private int size;

    /** Initialize your data structure here. */
    public MovingAverageDeque(int size) {
        deque = new ArrayDeque<>(size);
        this.size = size;
    }

    public double next(int val) {
        int former = 0;
        if (deque.size() >= size) {
            former = deque.pollFirst();
        }
        deque.offer(val);
        calSum(former, val);
        System.out.println(sum + ":" + deque.size());
        return (double) sum / deque.size();
    }

    private void calSum(int formerValue, int newValue) {
        sum += newValue;
        sum -= formerValue;
    }

    public static void main(String[] args) {
        MovingAverageDeque movingAverage = new MovingAverageDeque(3);
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}
