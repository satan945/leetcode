/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dfs.easy;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.List;

/**
 * @author abel created on 2017/9/22 下午5:31
 * @version $Id$
 */
public class Solution {

    /**
     * 339. Nested List Weight Sum
     * <p>
     * https://leetcode.com/problems/nested-list-weight-sum
     * <p>
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
     * 
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     * 
     * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
     * 
     * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 +
     * 4*2 + 6*3 = 27)
     * </p>
     * 
     * @param nestedList
     * @return
     */
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int res = 0;
        for (NestedInteger nestedInteger : nestedList) {
            int level = 1;
            res += calDepthSum(nestedInteger, level);
        }
        return res;
    }

    private int calDepthSum(NestedInteger nestedInteger, int level) {
        int res = 0;
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * level;
        } else {
            for (NestedInteger integer : nestedInteger.getList()) {
                res += calDepthSum(integer, level + 1);
            }
        }
        return res;
    }
}
