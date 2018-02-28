/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 *
 * https://leetcode.com/problems/find-median-from-data-stream
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 * </p>
 * 
 * @author abel created on 2018/2/24 下午2:32
 * @version $Id$
 */
public class FindMedianFromDataStream {
    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> big;

    public FindMedianFromDataStream() {
        small = new PriorityQueue<>(Comparator.reverseOrder());
        big = new PriorityQueue<>();
    }

    public void addNum(int num) {
        small.offer(num);
        big.offer(small.poll());
        if (big.size() > small.size()) {
            small.offer(big.poll());
        }
    }

    public double findMedian() {
        if (small.size() == big.size()) {
            return ((double) big.peek() + (double) small.peek()) / 2;
        }
        return (double) small.peek();

    }
}
