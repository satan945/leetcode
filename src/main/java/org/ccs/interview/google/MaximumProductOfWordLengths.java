/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * 318. Maximum Product of Word Lengths
 * 
 * @author abel created on 2018/2/28 下午12:03
 * @version $Id$
 */
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int[] values = new int[words.length];
        for (int i = 0; i < values.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                values[i] |= 1 << word.charAt(i) - 'a';
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if ((values[i] & values[j]) == 0) {
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        return maxProduct;
    }
}
