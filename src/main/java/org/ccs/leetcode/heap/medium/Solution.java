/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.heap.medium;

import org.ccs.leetcode.bean.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author abel created on 2017/11/22 下午9:25
 * @version $Id$
 */
public class Solution {

    /**
     * 313. Super Ugly Number
     * <p>
     * https://leetcode.com/problems/super-ugly-number
     * <p>
     * Write a program to find the nth super ugly number.
     * 
     * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For
     * example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given
     * primes = [2, 7, 13, 19] of size 4.
     * 
     * Note: (1) 1 is a super ugly number for any given primes. (2) The given numbers in primes are in ascending order.
     * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000. (4) The nth super ugly number is guaranteed to fit in a
     * 32-bit signed integer.
     * 
     * Credits: Special thanks to @dietpepsi for adding this problem and creating all test cases.
     * </p>
     * 
     * @param n
     * @param primes
     * @return
     */

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++)
            pq.add(new Num(primes[i], 1, primes[i]));
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            ugly[i] = pq.peek().val;
            while (pq.peek().val == ugly[i]) {
                Num nxt = pq.poll();
                pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
            }
        }

        return ugly[n - 1];
    }

    private class Num implements Comparable<Num> {
        int val;
        int idx;
        int p;

        public Num(int val, int idx, int p) {
            this.val = val;
            this.idx = idx;
            this.p = p;
        }

        @Override
        public int compareTo(Num that) {
            return this.val - that.val;
        }
    }

    /**
     * 253. Meeting Rooms II
     * <p>
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find
     * the minimum number of conference rooms required.
     * 
     * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
     * </p>
     * 
     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.start));
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, Comparator.comparingInt(o -> o.end));
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
        return heap.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] primes = new int[] { 2, 3, 5 };
        System.out.println(solution.nthSuperUglyNumber(1, primes));
    }
}