/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest63;

/**
 * @author abel created on 2018/1/30 下午4:39
 * @version $Id$
 */
public class ShortestCompletingWord {


    public String shortestCompletingWord(String licensePlate, String[] words) {
        String target = licensePlate.toLowerCase();
        String res = null;
        int[] targetCount = buildCount(target);
        for (String word : words) {
            int[] wordCount = buildCount(word);
            if (match(wordCount, targetCount) && (res == null || res.length() > word.length())) {
                res = word;
            }
        }
        return res;
    }

    private int[] buildCount(String target) {
        int[] count = new int[26];
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                count[ch - 'a']++;
            }
        }
        return count;
    }

    private boolean match(int[] wordCount, int[] targetCount) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] < targetCount[i]) {
                return false;
            }
        }
        return true;
    }
}
