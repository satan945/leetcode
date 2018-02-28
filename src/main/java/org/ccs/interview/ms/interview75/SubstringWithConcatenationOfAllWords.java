/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 *
 *
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 * <p>
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * 
 * For example, given:
 * 
 * s: "barfoothefoobarman"
 * 
 * words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 * </p>
 * 
 * @author abel created on 2018/2/23 下午3:22
 * @version $Id$
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        int wordLen = words[0].length();
        int arrayLen = words.length;
        int totalLen = wordLen * arrayLen;
        if (s.length() < totalLen) {
            return res;
        }
        Map<String, Integer> cntMap = new HashMap<>();
        for (String word : words) {
            cntMap.put(word, cntMap.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < s.length() - totalLen + 1; i++) {
            String subString = s.substring(i, i + totalLen);
            if (isMatch(subString, cntMap, wordLen)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isMatch(String subString, Map<String, Integer> cntMap, int wordLen) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < subString.length(); i += wordLen) {
            String key = subString.substring(i, i + wordLen);
            if (!cntMap.containsKey(key)) {
                return false;
            } else {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        for (String key : cntMap.keySet()) {
            if (!map.containsKey(key)) {
                return false;
            }
            if (!map.get(key).equals(cntMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0)
            return res;
        int len = words[0].length(); // length of each word

        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : words)
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);

        for (int i = 0; i <= s.length() - len * words.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < words.length; j++) { // checkc if match
                String str = s.substring(i + j * len, i + j * len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1)
                        copy.remove(str);
                    else
                        copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else
                    break; // not in L
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords solution = new SubstringWithConcatenationOfAllWords();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
        String a = "wordgoodgoodgoodbestword";
        String[] strings = new String[] { "word", "good", "best", "good" };
        System.out.println(solution.findSubstring(a, strings));
    }
}
