/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.PriorityQueue;

/**
 * 313. Super Ugly Number
 * 
 * https://leetcode.com/problems/super-ugly-number/description/
 * 
 * @author abel created on 2018/3/14 下午7:29
 * @version $Id$
 */
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] count = new int[primes.length];
        int[] tmp = new int[primes.length];
        for (int i = 0; i < primes.length; i++) {
            tmp[i] = primes[i];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
        for (int i = 0; i < primes.length; i++) {
            pq.offer(new int[] { i, tmp[i] });
        }
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = pq.peek()[1];
            while(pq.peek()[1] == res[i]){
                int[] pair = pq.poll();
                int idx = pair[0];
                count[idx]++;
                tmp[idx] = primes[idx] * res[count[idx]];
                pair[1] = tmp[idx];
                pq.offer(pair);
            }
        }
        return res[n - 1];
    }

    public static void main(String[] args) {
        int n = 12;
        int[] primes = new int[] { 2, 7, 13, 19 };
        SuperUglyNumber solution = new SuperUglyNumber();
        System.out.println(solution.nthSuperUglyNumber(n, primes));
    }
}
