/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 187. Repeated DNA Sequences
 *
 * https://leetcode.com/problems/repeated-dna-sequences
 *
 * @author abel created on 2018/3/13 下午8:30
 * @version $Id$
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if(!set.add(sub)){
                res.add(sub);
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        String a = "AAAAAAAAAAAA";
        RepeatedDNASequences solution = new RepeatedDNASequences();
        System.out.println(solution.findRepeatedDnaSequences(a));
    }
}
