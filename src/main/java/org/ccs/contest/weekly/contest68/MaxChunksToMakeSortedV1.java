/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest68;

import java.util.Arrays;

/**
 * 769. Max Chunks To Make Sorted (ver. 1)
 * 
 * @author abel created on 2018/1/20 下午7:18
 * @version $Id$
 */
public class MaxChunksToMakeSortedV1 {

    public int maxChunksToSorted(int[] arr) {
        int[] max = new int[arr.length];
        int[] min = new int[arr.length];
        int res = 1;
        max[0] = arr[0];
        min[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i - 1]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            min[i] = Math.min(min[i + 1], arr[i]);
        }
        for (int i = 1; i < arr.length; i++) {
            if (max[i] <= min[i]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSortedV1 maxChunksToMakeSortedV1 = new MaxChunksToMakeSortedV1();
        System.out.println(maxChunksToMakeSortedV1.maxChunksToSorted(new int[] { 0, 0, 1, 1, 1 }));
        System.out.println(maxChunksToMakeSortedV1.maxChunksToSorted(new int[] { 1, 0, 2, 3, 4 }));
    }
}
