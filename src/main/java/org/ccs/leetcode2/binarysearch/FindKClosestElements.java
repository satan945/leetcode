/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.binarysearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 658. Find K Closest Elements
 * <p>
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be
 * sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * </p>
 * 
 * @author Abel created on 2018/1/6 11:03
 * @version $Id$
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0, end = arr.length - k;

        while (start < end) {
            int mid = (start + end) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = start; i < start + k; i++) {
            results.add(arr[i]);
        }
        return results;
    }

    public static void main(String[] args) {
        FindKClosestElements solution = new FindKClosestElements();
        System.out.println(solution.findClosestElements(new int[] { 0, 0, 0, 1, 3, 5, 6, 7, 8, 8 }, 2, 2));
    }

}
