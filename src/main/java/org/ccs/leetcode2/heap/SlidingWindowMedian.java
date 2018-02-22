/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 480. Sliding Window Median
 * 
 * https://leetcode.com/problems/sliding-window-median
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your
 * job is to output the median array for each window in the original array.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * </p>
 * 
 * @author abel created on 2018/2/21 下午3:43
 * @version $Id$
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Long> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> big = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                addNumber(small, big, nums[i]);
                if (i == k - 1) {
                    res[0] = getMedian(small, big, k);
                }
            } else {
                delNumber(small, big, nums[i - k]);
                addNumber(small, big, nums[i]);
                res[i - k + 1] = getMedian(small, big, k);
            }
        }
        return res;
    }

    private double getMedian(PriorityQueue<Long> small, PriorityQueue<Long> big, int k) {
        if (k % 2 == 0) {
            return (double) (small.peek() + big.peek()) / 2;
        }
        return (double) small.peek();
    }

    private void delNumber(PriorityQueue<Long> small, PriorityQueue<Long> big, int num) {
        long longVal = (long) (num);
        if (small.remove(longVal)) {
            if (big.size() > small.size()) {
                small.offer(big.poll());
            }
        } else {
            big.remove(longVal);
        }
    }

    private void addNumber(PriorityQueue<Long> small, PriorityQueue<Long> big, int num) {
        small.offer((long) num);
        big.offer(small.poll());
        if (big.size() > small.size()) {
            small.offer(big.poll());
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian solution = new SlidingWindowMedian();
        double[] res = solution.medianSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3);
        System.out.println(res);
    }
}
