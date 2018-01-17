/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import java.util.List;

import org.ccs.leetcode.bean.NestedInteger;

/**
 * 364. Nested List Weight Sum II
 * <p>
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
 * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * 
 * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 +
 * 4*2 + 6*1 = 17)
 * </p>
 * 
 * @author abel created on 2018/1/16 下午9:21
 * @version $Id$
 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int maxLevel = 0;
        for (NestedInteger nestedInteger : nestedList) {
            maxLevel = Math.max(maxLevel, dfsCalLevel(1, nestedInteger));
        }
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            sum += dfsSum(nestedInteger, maxLevel);
        }
        return sum;
    }

    private int dfsSum(NestedInteger nestedInteger, int level) {
        int sum = 0;
        if (nestedInteger.isInteger()) {
            sum += nestedInteger.getInteger() * level;
        } else {
            for (NestedInteger integer : nestedInteger.getList()) {
                sum += dfsSum(integer, level - 1);
            }
        }
        return sum;
    }

    private int dfsCalLevel(int curLevel, NestedInteger nestedInteger) {
        int level = 0;
        if (nestedInteger.isInteger()) {
            level = curLevel;
        } else {
            for (NestedInteger integer : nestedInteger.getList()) {
                level = Math.max(level, dfsCalLevel(curLevel + 1, integer));
            }
        }
        return level;
    }
}
