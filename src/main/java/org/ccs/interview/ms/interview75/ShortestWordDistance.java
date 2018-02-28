/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

/**
 * 243. Shortest Word Distance
 * 
 * https://leetcode.com/problems/shortest-word-distance
 * <p>
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
 * list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
 * </p>
 * 
 * @author abel created on 2018/2/23 下午3:52
 * @version $Id$
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int min = words.length + 1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            }
            if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                min = Math.min(min, Math.abs(index1 - index2));
            }
        }
        return min;

    }
}
