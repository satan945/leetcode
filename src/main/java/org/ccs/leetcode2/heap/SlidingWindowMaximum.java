/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 239. Sliding Window Maximum
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/6 上午9:52
 * @version $Id$
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = nums[q.peek()];
            }
        }
        return r;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        Queue<Integer> window = new LinkedList<>();
        Queue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                window.offer(nums[i]);
                pq.offer(nums[i]);
            } else {
                res[i - k] = pq.peek();
                Integer first = window.poll();
                pq.remove(first);
                window.offer(nums[i]);
                pq.offer(nums[i]);
            }
        }
        res[res.length - 1] = pq.peek();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        int[] res = solution.maxSlidingWindow(nums, 3);
        System.out.println(res);
    }

}
