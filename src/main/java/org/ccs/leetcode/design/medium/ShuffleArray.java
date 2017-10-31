/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

/**
 * @author abel created on 2017/10/4 下午6:32
 * @version $Id$
 */

import java.util.Random;

/**
 * 384. Shuffle an Array
 * <p>
 * https://leetcode.com/problems/shuffle-an-array
 * <p>
 * Shuffle a set of numbers without duplicates.
 * 
 * Example:
 * 
 * // Init an array with set 1, 2, and 3. int[] nums = {1,2,3}; Solution solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3]. solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3]. solution.shuffle();
 * </p>
 */
public class ShuffleArray {
    private int[] array;
    private Random random;

    public ShuffleArray(int[] nums) {
        this.array = nums;
        random = new Random();
    }

    public int[] reset() {
        return array;
    }

    public int[] shuffle() {
        if (array == null) {
            return null;
        }
        int[] res = array.clone();
        for (int j = 1; j < res.length; j++) {
            int i = random.nextInt(j + 1);
            swap(array, i, j);
        }
        return res;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        ShuffleArray shuffleArray = new ShuffleArray(new int[] { 1, 2, 3, });
        for (int i = 0; i < 10; i++) {
            int[] res = shuffleArray.shuffle();
            System.out.printf(res[0] + "," + res[1] + "," + res[2]+"\n");
        }
    }
}
