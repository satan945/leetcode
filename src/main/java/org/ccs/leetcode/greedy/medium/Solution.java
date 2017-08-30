/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.greedy.medium;

/**
 * @author abel created on 2017/8/29 下午6:58
 * @version $Id$
 */
public class Solution {

    /**
     * 55. Jump Game
     *
     * <p>
     * https://leetcode.com/problems/jump-game
     * <p>
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Determine if you are able to reach the last index.
     *
     * For example:
     *
     * A = [2,3,1,1,4], return true.
     *
     * A = [3,2,1,0,4], return false.
     * </p>
     * https://leetcode.com/problems/jump-game/solution/
     * 
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int maxReach = 0;
        for (int i = 0; i < nums.length && i <= maxReach; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean canJumpRight(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

}
