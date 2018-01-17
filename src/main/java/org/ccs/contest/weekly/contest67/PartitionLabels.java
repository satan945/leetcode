/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest67;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 * <p>
 * No.2
 * 
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Note:
 * 
 * 1.S will have length in range [1, 500].
 * 
 * 2.S will consist of lowercase letters ('a' to 'z') only.
 * </p>
 * 
 * @author abel created on 2018/1/16 下午5:19
 * @version $Id$
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int[] table = new int[26];
        for (int i = 0; i < S.length(); i++) {
            table[S.charAt(i) - 'a'] = i;
        }
        int start = 0, last = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, table[S.charAt(i) - 'a']);
            if (last == i) {
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
        System.out.println(partitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
