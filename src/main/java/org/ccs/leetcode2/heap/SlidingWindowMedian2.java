/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author abel created on 2018/2/23 下午2:35
 * @version $Id$
 */
public class SlidingWindowMedian2 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k <= 0) {
            return new double[0];
        }
        int len = nums.length;
        double[] res = new double[len - k + 1];
        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> big = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            addNumber(nums[i], small, big);
            if (i >= k - 1) {
                int left = i - k;
                if (left >= 0) {
                    delNumber(nums[left], small, big);
                }
                res[i - k + 1] = getMedian(small, big);
            }
        }
        return res;
    }

    private void addNumber(int num, PriorityQueue<Integer> small, PriorityQueue<Integer> big) {
        small.offer(num);
        big.offer(small.poll());
        if (big.size() > small.size()) {
            small.offer(big.poll());
        }
    }

    private void delNumber(int num, PriorityQueue<Integer> small, PriorityQueue<Integer> big) {
        if (small.remove(num)) {
            if (big.size() > small.size()) {
                small.offer(big.poll());
            }
        } else {
            big.remove(num);
            if (small.size() - 1 > big.size()) {
                big.offer(small.poll());
            }
        }
    }

    private double getMedian(PriorityQueue<Integer> small, PriorityQueue<Integer> big) {
        if (small.size() == big.size()) {
            return ((double) small.peek() + (double) big.peek()) / 2.0;
        } else {
            return (double) small.peek();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648,
                2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648 };
        int[] nums2 = new int[] { -2147483648, -2147483648, 2147483647, -2147483648, 1, 3, -2147483648, -100, 8, 17, 22,
                -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647,
                -2147483648 };
        int[] nums3 = new int[] { 2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647 };
        // int[] nums=new int[]{-1,-1,1,-1,-1,-1,1,1,1};
        SlidingWindowMedian2 solution = new SlidingWindowMedian2();
        double[] res = solution.medianSlidingWindow(nums2, 6);
        System.out.println(res);

    }
}
