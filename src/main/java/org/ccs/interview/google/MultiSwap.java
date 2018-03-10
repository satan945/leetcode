/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 2, follow up , 如果不只一组swap, 有多组swap怎么办，我说用hashmap，还是走一遍。.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
 * 
 * @author abel created on 2018/3/4 下午9:50
 * @version $Id$
 */
public class MultiSwap {

    public boolean isMultiSwap(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sChar != tChar) {
                if (!check(sChar, tChar, map)) {
                    return false;
                }
                map.putIfAbsent(sChar, tChar);
                map.putIfAbsent(tChar, sChar);
            }
        }
        return true;
    }

    private boolean check(char sChar, char tChar, Map<Character, Character> map) {
        if (map.containsKey(sChar)) {
            if (map.get(sChar) != tChar) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MultiSwap multiSwap = new MultiSwap();
        System.out.println(multiSwap.isMultiSwap("ABC", "CAB"));
        System.out.println(multiSwap.isMultiSwap("conSerVe", "conVerSe"));
    }
}
