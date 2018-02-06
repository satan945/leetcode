/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 565. Array Nesting
 * <p>
 * A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S,
 * where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
 * 
 * Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should
 * be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.
 * </p>
 * 
 * @author abel created on 2018/2/1 下午5:11
 * @version $Id$
 */
public class ArrayNesting {

    public int arrayNesting(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int len = 0;
        Set<Integer> totalUsed = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> used = new HashSet<>();
            if (totalUsed.contains(i)) {
                continue;
            }
            while (used.add(i)) {
                i = nums[i];
            }
            totalUsed.addAll(used);
            len = Math.max(used.size(), len);
            if (len >= (nums.length + 1) / 2) {
                return len;
            }
        }
        return len;
    }

    public int arrayNesting2(int[] nums) {
        int maxsize = 0;
        for (int i = 0; i < nums.length; i++) {
            int size = 0;
            for (int k = i; nums[k] >= 0; size++) {
                int ak = nums[k];
                nums[k] = -1; // mark a[k] as visited;
                k = ak;
            }
            maxsize = Integer.max(maxsize, size);
        }

        return maxsize;
    }

    public static void main(String[] args) {
        ArrayNesting solution = new ArrayNesting();
        System.out.println(solution.arrayNesting(new int[] { 5, 4, 0, 3, 1, 6, 2 }));
    }
}
