/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

/**
 * @author Abel created on 2017/9/7 12:42
 * @version $Id$
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This is a follow up of Shortest Word Distance.
 * 
 * https://leetcode.com/problems/shortest-word-distance
 * 
 * The only difference is now you are given the list of words and your method will be called repeatedly many times with
 * different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 
 * 
 * </p>
 */
public class WordDistance {
    private Map<String, List<Integer>> indexMap;

    public WordDistance(String[] words) {
        indexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!indexMap.containsKey(word)) {
                indexMap.put(word, new ArrayList<>());
            }
            indexMap.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> index1List = indexMap.get(word1);
        List<Integer> index2List = indexMap.get(word2);
        int res = Integer.MAX_VALUE;
        if (index1List == null || index2List == null) {
            return -1;
        }
        for (int i = 0; i < index1List.size(); i++) {
            for (int j = 0; j < index2List.size(); j++) {
                res = Math.min(Math.abs(index1List.get(i) - index2List.get(j)), res);
            }
        }
        return res;
    }
}
