/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.greedy.medium;

import java.util.ArrayList;
import java.util.Arrays;

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

    /**
     * 484. Find Permutation
     * <p>
     * https://leetcode.com/problems/find-permutation
     * <p>
     * 
     * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing
     * relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret
     * signature was constructed by a special integer array, which contains uniquely all the different number from 1 to
     * n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by
     * array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal
     * constructing special string that can't represent the "DI" secret signature.
     * 
     * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could
     * refer to the given secret signature in the input.
     * 
     * Example 1:
     * 
     * Input: "I" Output: [1,2]
     * 
     * Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number
     * 1 and 2 construct an increasing relationship.
     * 
     * Example 2: Input: "DI" Output: [2,1,3]
     * 
     * Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", but since we want to find the one
     * with the smallest lexicographical permutation, you need to output [2,1,3]
     * 
     * Note:
     * 
     * The input string will only contain the character 'D' and 'I'. The length of input string is a positive integer
     * and will not exceed 10,000
     * 
     * </p>
     * todo
     * 
     * @param s
     * @return
     */
    public int[] findPermutation(String s) {
        int[] res = new int[0];
        return res;
    }

    /**
     * 621. Task Scheduler
     * <p>
     * https://leetcode.com/problems/task-scheduler
     * <p>
     * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters
     * represent different tasks.Tasks could be done without original order. Each task could be done in one interval.
     * For each interval, CPU could finish one task or just be idle.
     * 
     * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n
     * intervals that CPU are doing different tasks or just be idle.
     * 
     * You need to return the least number of intervals the CPU will take to finish all the given tasks.
     * </p>
     * https://discuss.leetcode.com/topic/92852/concise-java-solution-o-n-time-o-26-space
     * 
     * https://leetcode.com/articles/task-scheduler/
     * 
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char t : tasks) {
            count[t - 'A']++;
        }
        Arrays.sort(count);
        int i = 25;
        while (i >= 0 && count[i] == count[25]) {
            i--;
        }
        return Math.max(tasks.length, (count[25] - 1) * (n + 1) + 25 - i);
    }

    /**
     * 406. Queue Reconstruction by Height
     * <p>
     * https://leetcode.com/problems/queue-reconstruction-by-height
     * <p>
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h,
     * k), where h is the height of the person and k is the number of people in front of this person who have a height
     * greater than or equal to h. Write an algorithm to reconstruct the queue.
     * 
     * Note: The number of people is less than 1,100.
     * 
     * Example
     * 
     * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * 
     * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     * </p>
     * 
     * @param people
     * @return
     */

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });
        ArrayList<int[]> resList = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            resList.add(people[i][1], new int[] { people[i][0], people[i][1] });
        }
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            res[i][0] = resList.get(i)[0];
            res[i][1] = resList.get(i)[1];
        }
        return res;
    }

    /**
     * 134. Gas Station
     * <p>
     * https://leetcode.com/problems/gas-station
     * <p>
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * 
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * 
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * 
     * Note: The solution is guaranteed to be unique.
     * 
     * </p>
     * 
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }
        int start = 0, total = 0, tank = 0;
        for (int i = 0; i < gas.length; i++) {
            tank = tank + gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return total + tank < 0 ? -1 : start;
    }

    /**
     * 649. Dota2 Senate
     * 
     * <p>
     * https://leetcode.com/problems/dota2-senate
     * <p>
     * In the world of Dota2, there are two parties: the Radiant and the Dire.
     * 
     * The Dota2 senate consists of senators coming from two parties. Now the senate wants to make a decision about a
     * change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can
     * exercise one of the two rights:
     * 
     * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following
     * rounds. Announce the victory: If this senator found the senators who still have rights to vote are all from the
     * same party, he can announce the victory and make the decision about the change in the game. Given a string
     * representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire
     * party respectively. Then if there are n senators, the size of the given string will be n.
     * 
     * The round-based procedure starts from the first senator to the last senator in the given order. This procedure
     * will last until the end of voting. All the senators who have lost their rights will be skipped during the
     * procedure.
     * 
     * Suppose every senator is smart enough and will play the best strategy for his own party, you need to predict
     * which party will finally announce the victory and make the change in the Dota2 game. The output should be Radiant
     * or Dire.
     * 
     * Example 1:
     * 
     * Input: "RD" Output: "Radiant"
     * 
     * Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
     * And the second senator can't exercise any rights any more since his right has been banned. And in the round 2,
     * the first senator can just announce the victory since he is the only guy in the senate who can vote.
     * 
     * Example 2:
     * 
     * Input: "RDD" Output: "Dire"
     * 
     * Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
     * And the second senator can't exercise any rights anymore since his right has been banned. And the third senator
     * comes from Dire and he can ban the first senator's right in the round 1. And in the round 2, the third senator
     * can just announce the victory since he is the only guy in the senate who can vote.
     * </p>
     * todo
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        return "";

    }

    public static void main(String[] args) {
        int[][] nums = { { 7, 1 }, { 4, 4 }, { 7, 0 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
        // new Solution().reconstructQueue(nums);
    }

}
