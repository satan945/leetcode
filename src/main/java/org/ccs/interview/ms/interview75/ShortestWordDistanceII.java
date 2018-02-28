/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. Shortest Word Distance II
 * <p>
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your
 * method will be called repeatedly many times with different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
 * </p>
 * 
 * @author abel created on 2018/2/23 下午3:55
 * @version $Id$
 */
public class ShortestWordDistanceII {

    private Map<String, List<Integer>> indexMap;

    public ShortestWordDistanceII(String[] words) {
        indexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            indexMap.putIfAbsent(words[i], new ArrayList<>());
            indexMap.get(words[i]).add(i);
        }

    }

    public int shortest(String word1, String word2) {
        List<Integer> index1List = indexMap.get(word1);
        List<Integer> index2List = indexMap.get(word2);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < index1List.size(); i++) {
            for (int j = 0; j < index2List.size(); j++) {
                res = Math.min(res, Math.abs(index1List.get(i) - index2List.get(j)));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = new String[] { "practice", "makes", "perfect", "coding", "makes" };
        ShortestWordDistanceII solution = new ShortestWordDistanceII(strings);
        System.out.println(solution.shortest("coding", "practice"));
        System.out.println(solution.shortest("makes", "coding"));
    }
}
