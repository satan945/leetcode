/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.List;

/**
 * 339. Nested List Weight Sum
 * 
 * @author abel created on 2018/1/16 下午9:22
 * @version $Id$
 */
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            sum += dfsSum(nestedInteger, 1);
        }
        return sum;
    }

    private int dfsSum(NestedInteger nestedInteger, int level) {
        int sum = 0;
        if (nestedInteger.isInteger()) {
            sum += nestedInteger.getInteger() * level;
        } else {
            for (NestedInteger integer : nestedInteger.getList()) {
                sum += dfsSum(integer, level + 1);
            }
        }
        return sum;
    }



}
