/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

/**
 * 245. Shortest Word Distance III
 * 
 * https://leetcode.com/problems/shortest-word-distance-iii/description/
 * <p>
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * 
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
 * list.
 * 
 * word1 and word2 may be the same and they represent two individual words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
 * 
 * Note: You may assume word1 and word2 are both in the list.
 * </p>
 * 
 * @author abel created on 2018/2/23 下午4:18
 * @version $Id$
 */
public class ShortestWordDistanceIII {

    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) {
            return -1;
        }
        int index1 = -1;
        int index2 = -1;
        int res = words.length;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (!same) {
                if (word1.equals(words[i])) {
                    index1 = i;
                }
                if (word2.equals(words[i])) {
                    index2 = i;
                }
            } else {
                if (word1.equals(words[i])) {
                    if (index2 != -1) {
                        index1 = index2;
                    }
                    index2 = i;
                }
            }
            if (index1 >= 0 && index2 >= 0) {
                res = Math.min(res, Math.abs(index2 - index1));
            }
        }
        return res < words.length ? res : -1;
    }

    public static void main(String[] args) {
        String[] words = new String[] { "practice", "makes", "perfect", "coding", "makes" };
        String[] words2 = new String[] { "a", "c", "a", "a" };
        ShortestWordDistanceIII solution = new ShortestWordDistanceIII();
        System.out.println(solution.shortestWordDistance(words2, "a", "a"));
    }
}
