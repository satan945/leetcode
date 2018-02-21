/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 784. Letter Case Permutation
 * <p>
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * </p>
 * 
 * @author abel created on 2018/2/20 下午4:52
 * @version $Id$
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        permute(result, S.toCharArray(), 0);
        return new ArrayList<>(result);
    }

    private void permute(List<String> result, char[] chars, int pos) {
        if (pos == chars.length) {
            result.add(String.valueOf(chars));
        } else {
            permute(result, chars, pos + 1);
            if (Character.isLetter(chars[pos])) {
                changeCase(chars, pos);
                permute(result, chars, pos + 1);
                changeCase(chars, pos);
            }
        }
    }

    private void changeCase(char[] chars, int i) {
        if (Character.isLowerCase(chars[i])) {
            chars[i] = Character.toUpperCase(chars[i]);
        } else {
            chars[i] = Character.toLowerCase(chars[i]);
        }
    }

    public List<String> letterCasePermutationBFS(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        for (int i = 0; i < S.length(); i++) {
            if (!Character.isLetter(S.charAt(i))) {
                continue;
            }
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String str = queue.poll();
                char[] chars = str.toCharArray();
                queue.offer(String.valueOf(chars));
                changeCase(chars, j);
                queue.offer(String.valueOf(chars));
            }
        }
        return new LinkedList<>(queue);
    }

    public static void main(String[] args) {
        String string = "a1b2c3";
        LetterCasePermutation letterCasePermutation = new LetterCasePermutation();
        System.out.println(letterCasePermutation.letterCasePermutation(string));
        System.out.println(letterCasePermutation.letterCasePermutationBFS(string));
    }
}
