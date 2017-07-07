/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.unsorted.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/7/7 11:51
 * @version $Id$
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                } else {
                    System.out.println("i=" + i + ";;;j=" + j);
                }
            }
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int record = 0;
        String recordStr = "";
        for (int i = 0; i < s.length(); i++) {
            char[] cur = new char[1];
            cur[0] = s.charAt(i);
            if (!recordStr.contains(new String(cur))) {
                recordStr = recordStr + s.charAt(i);
                record++;
                if (max < record) {
                    max = record;
                }
            } else {
                if (record > max) {
                    max = record;
                }
                int index = s.indexOf(cur[0]);
                String substring = s.substring(index + 1);
                record = substring.length();
                recordStr = substring;
                System.out.println(index);
                System.out.println(record);
                System.out.println(recordStr);
            }
        }
        return max;
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = result[i / 2] + i % 2;
        }
        return result;
    }

    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if (nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }
        if (nums.length == 3) {
            return nums[0] + "/(" + nums[1] + "/" + nums[2] + ")";
        }
        StringBuilder sb = new StringBuilder().append(nums[0]).append("/(").append(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sb.append("/").append(nums[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int maxLocation = 0;
        int minLocation = 0;
        int[] minNums = new int[arrays.size()];
        int[] maxNums = new int[arrays.size()];

        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            maxNums[i] = list.get((list.size() - 1));
            minNums[i] = list.get(0);
            if (max < list.get(list.size() - 1)) {
                max = list.get(list.size() - 1);
                maxLocation = i;
            }
            if (min > list.get(0)) {
                min = list.get(0);
                minLocation = i;
            }
        }

        System.out.println(maxLocation + ":" + minLocation);
        System.out.println(max + ":" + min);
        if (maxLocation != minLocation) {
            return max - min;
        } else {
            Arrays.sort(minNums);
            Arrays.sort(maxNums);
            int result1 = max - minNums[1];
            int result2 = maxNums[arrays.size() - 2] - min;
            return result1 > result2 ? result1 : result2;
        }

    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int curR = nums.length;
        int curC = nums[0].length;
        if (r * c != curR * curC) {
            return nums;
        }
        int[][] result = new int[r][c];
        int[] oneRow = new int[r * c];
        for (int i = 0; i < nums.length; i++) {
            int[] row = nums[i];
            for (int j = 0; j < row.length; j++) {
                oneRow[i * curC + j] = row[j];
            }
        }

        for (int i = 0; i < r; i++) {
            int[] resultRow = result[i];
            for (int j = 0; j < resultRow.length; j++) {
                resultRow[j] = oneRow[i * c + j];
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (stack.size() != 0 || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            while (!stack.isEmpty()) {
                node = stack.peek();

            }
        }
        return null;

    }

    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < candies.length; i++) {
            set.add(candies[i]);
        }
        return set.size() < (candies.length / 2) ? set.size() : candies.length / 2;
    }

    public int longestPalindrome(String s) {
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            Integer count = countMap.get(a);
            if (count == null) {
                countMap.put(a, 1);
            } else {
                countMap.put(a, count + 1);
            }
        }
        int maxLength = 0;
        boolean hasOdd = false;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                maxLength += entry.getValue();
            } else {
                hasOdd = true;
                maxLength += (entry.getValue() - 1);
            }
        }
        if (hasOdd) {
            maxLength++;
        }
        return maxLength;
    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i * 2 + 1 < nums.length; i++) {
            if (nums[2 * i] != nums[2 * i + 1]) {
                return nums[2 * i];
            }
        }
        return nums[nums.length - 1];
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        for (int i = 0; i < s.length(); i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }
        return true;

    }

}
