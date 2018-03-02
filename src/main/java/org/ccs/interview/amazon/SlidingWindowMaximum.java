/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.amazon;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 239. Sliding Window Maximum
 * 
 * https://leetcode.com/problems/sliding-window-maximum
 * 
 * @author abel created on 2018/2/28 上午11:17
 * @version $Id$
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k <= 0) {
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                res[i - k + 1] = res[deque.peek()];
            }
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k <= 0) {
            return new int[0];
        }
        Queue<Integer> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            pq.offer(nums[i]);
            if (i > k - 1) {
                Integer left = queue.poll();
                pq.remove(left);
            }
            if (i >= k - 1) {
                res[i - k + 1] = pq.peek();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        System.out.println(solution.maxSlidingWindow2(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3));
    }

}
