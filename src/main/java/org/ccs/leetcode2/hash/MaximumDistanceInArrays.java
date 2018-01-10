/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.hash;

import java.util.List;

/**
 * 624. Maximum Distance in Arrays
 * <p>
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different
 * arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be
 * their absolute difference |a-b|. Your task is to find the maximum distance.
 * </p>
 * 
 * @author abel created on 2018/1/9 下午7:34
 * @version $Id$
 */
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size() - 1);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < arrays.size(); i++) {
            res = Math.max(res, arrays.get(i).get(arrays.get(i).size() - 1) - min);
            res = Math.max(res, max - arrays.get(i).get(0));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }
        return res;

    }
}
