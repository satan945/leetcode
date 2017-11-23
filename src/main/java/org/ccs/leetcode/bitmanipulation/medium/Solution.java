/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.bitmanipulation.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Abel created on 2017/9/20 17:49
 * @version $Id$
 */
public class Solution {

    /**
     * 137. Single Number II
     * <p>
     * https://leetcode.com/problems/single-number-ii
     * <p>
     * Given an array of integers, every element appears three times except for one, which appears exactly once. Find
     * that single one.
     * 
     * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * </p>
     * https://discuss.leetcode.com/topic/11877/detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
     * 
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }

    /**
     * 260. Single Number III
     * <p>
     * https://leetcode.com/problems/single-number-iii
     * <p>
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
     * exactly twice. Find the two elements that appear only once.
     * 
     * For example:
     * 
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     * 
     * Note: The order of the result is not important. So in the above example, [5, 3] is also correct. Your algorithm
     * should run in linear runtime complexity. Could you implement it using only constant space complexity?
     * 
     * </p>
     * 
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = { 0, 0 }; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            } else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

    /**
     * 89. Gray Code
     * <p>
     * https://leetcode.com/problems/gray-code
     * <p>
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     *
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray
     * code. A gray code sequence must begin with 0.
     *
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     *
     * 00 - 0 01 - 1 11 - 3 10 - 2 Note: For a given n, a gray code sequence is not uniquely defined.
     *
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     *
     * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     *
     * ¬
     * </p>
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }

    /**
     * 421. Maximum XOR of Two Numbers in an Array
     * <p>
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array
     * <p>
     * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
     * 
     * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
     * 
     * Could you do this in O(n) runtime?
     * 
     * Example:
     * 
     * Input: [3, 10, 5, 25, 2, 8]
     * 
     * Output: 28
     * 
     * Explanation: The maximum result is 5 ^ 25 = 28.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 318. Maximum Product of Word Lengths
     * <p>
     * https://leetcode.com/problems/maximum-product-of-word-lengths
     * <p>
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do
     * not share common letters. You may assume that each word will contain only lower case letters. If no such two
     * words exist, return 0.
     * 
     * Example 1: Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"] Return 16 The two words can be "abcw", "xtfn".
     * 
     * Example 2: Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"] Return 4 The two words can be "ab", "cd".
     * 
     * Example 3: Given ["a", "aa", "aaa", "aaaa"] Return 0 No such pair of words.
     * </p>
     * 
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int len = words.length;
        int[] values = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            values[i] = 0;
            for (int j = 0; j < word.length(); j++) {
                values[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((values[i] & values[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
}
