/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. Find Median from Data Stream
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data structure. double findMedian() - Return
 * the median of all elements so far.
 * </p>
 * 
 * @author abel created on 2018/2/7 下午2:15
 * @version $Id$
 */
public class MedianFinder {
    private Queue<Integer> small;
    private Queue<Integer> large;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        large = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    }

    public void addNum(int num) {
        small.offer(num);
        large.offer(small.poll());
        if (large.size() > small.size()) {
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        int size = small.size() + large.size();
        if (size % 2 == 0) {
            return (double) (small.peek() + large.peek()) / 2;
        } else {
            return (double) small.peek();
        }
    }

    public static void main(String[] args) {

    }
}
