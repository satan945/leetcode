/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abel created on 2018/1/10 下午6:46
 * @version $Id$
 */
public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }
        Map<String, String> mapping = new HashMap<>();
        for (String[] pair : pairs) {
            mapping.putIfAbsent(pair[0], pair[0]);
            mapping.putIfAbsent(pair[1], pair[1]);
            setParent(mapping, pair[0], pair[1]);
        }
        int len = words1.length;
        for (int i = 0; i < len; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            String p1 = getParent(mapping, word1);
            String p2 = getParent(mapping, word2);
            if (!p1.equals(p2)) {
                return false;
            }
        }
        return true;
    }

    private void setParent(Map<String, String> mapping, String word1, String word2) {
        String p1 = getParent(mapping, word1);
        String p2 = getParent(mapping, word2);
        mapping.put(p1, p2);
    }

    private String getParent(Map<String, String> mapping, String word) {
        if (!mapping.containsKey(word)) {
            return word;
        }
        while (!word.equals(mapping.get(word))) {
            word = mapping.get(word);
        }
        return word;
    }

    public static void main(String[] args) {
        String[][] pairs = { { "good", "great" }, { "fine", "good" }, { "acting", "drama" }, { "skills", "talent" } };
        String[] words1 = new String[] { "fine", "drama", "talent" };
        String[] words2 = new String[] { "great", "acting", "skills" };
        SentenceSimilarityII solution = new SentenceSimilarityII();
        System.out.println(solution.areSentencesSimilarTwo(words1, words2, pairs));
    }
}
