/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 356. Line Reflection
 * <p>
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 * 
 * Example 1: Given points = [[1,1],[-1,1]], return true.
 * 
 * Example 2: Given points = [[1,1],[-1,-1]], return false.
 * 
 * Follow up: Could you do better than O(n2)?
 * </p>
 * 
 * @author abel created on 2018/1/17 上午11:41
 * @version $Id$
 */
public class LineReflection {

    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            set.add(point[0] + ":" + point[1]);
        }
        int sum = max + min;
        for (int[] point : points) {
            if (!set.contains(sum - point[0] + ":" + point[1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LineReflection lineReflection = new LineReflection();
        System.out.println(lineReflection.isReflected(new int[][] { { 0, 0 }, { 0, 0 } }));
    }

}
