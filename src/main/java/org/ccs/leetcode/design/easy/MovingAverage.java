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
public class MovingAverage {
    private long sum;
    private int[] window;
    private int num, insert;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];
        insert = 0;
        sum = 0;
    }

    public double next(int val) {
        if (num < window.length) {
            num++;
        }
        sum -= window[insert];
        sum += val;
        window[insert] = val;
        insert = (insert + 1) % window.length;
        return (double) sum / num;
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}
