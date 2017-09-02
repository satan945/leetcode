/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author abel created on 2017/8/29 下午4:43
 * @version $Id$
 */
public class Solution {

    /**
     * 599. Minimum Index Sum of Two Lists
     * <p>
     * https://leetcode.com/problems/minimum-index-sum-of-two-lists
     * <p>
     * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants
     * represented by strings.
     *
     * You need to help them find out their common interest with the least list index sum. If there is a choice tie
     * between answers, output all of them with no order requirement. You could assume there always exists an answer.
     *
     * Example 1:
     *
     * Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter
     * Steakhouse", "Shogun"]
     *
     * Output: ["Shogun"]
     *
     * Explanation: The only restaurant they both like is "Shogun".
     *
     * Example 2:
     *
     * Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["KFC", "Shogun", "Burger King"]
     *
     * Output: ["Shogun"]
     *
     * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1). Note:
     * The length of both lists will be in the range of [1, 1000]. The length of strings in both lists will be in the
     * range of [1, 30]. The index is starting from 0 to the list length minus 1. No duplicates in both lists.
     * </p>
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            String name = list1[i];
            map.put(name, i);
        }
        int minSum = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String name = list2[i];
            if (map.containsKey(name)) {
                int count = map.get(name);
                if (count + i < minSum) {
                    res.clear();
                    minSum = count + i;
                }
                if (count + i <= minSum) {
                    res.add(name);
                }
            }

        }
        return res.toArray(new String[res.size()]);
    }

    /**
     * 463. Island Perimeter
     * <p>
     * https://leetcode.com/problems/island-perimeter
     * <p>
     * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
     * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
     * and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water
     * inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is
     * rectangular, width and height don't exceed 100. Determine the perimeter of the island.
     * 
     * Example:
     * 
     * [[0,1,0,0], [1,1,1,0], [0,1,0,0], [1,1,0,0]]
     * 
     * Answer: 16
     *
     * Explanation: The perimeter is the 16 yellow stripes in the image below:
     * </p>
     * 
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        return 0;

    }
}
