/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.geeks.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author abel created on 2018/1/9 下午5:42
 * @version $Id$
 */
public class ClosestNumbersInUnsortedArray {

    public List<int[]> closestNumbers(int[] array) {
        if (array == null || array.length < 2) {
            return new ArrayList<>();
        }
        List<int[]> res = new ArrayList<>();
        Arrays.sort(array);
        int sub = Integer.MAX_VALUE;
        for (int i = 1; i < array.length; i++) {
            sub = Math.min(array[i] - array[i - 1], sub);
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] - array[i - 1] == sub) {
                res.add(new int[] { array[i - 1], array[i] });
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3, 4, 5 };
        ClosestNumbersInUnsortedArray solution = new ClosestNumbersInUnsortedArray();
        System.out.printf(String.valueOf(solution.closestNumbers(array)));
    }
}
