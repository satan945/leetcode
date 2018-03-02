/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. Unique Binary Search Trees
 * 
 * https://leetcode.com/problems/unique-binary-search-trees
 * 
 * @author abel created on 2018/2/28 下午12:21
 * @version $Id$
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 1);
        return countTrees(n, map);
    }

    private int countTrees(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int left = countTrees(i - 1, map);
            int right = countTrees(n - i, map);
            sum += left * right;
        }
        map.put(n, sum);
        return sum;
    }

    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
