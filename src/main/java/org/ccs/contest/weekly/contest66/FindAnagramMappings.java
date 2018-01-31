/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest66;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 760. Find Anagram Mappings
 * 
 * @author abel created on 2018/1/30 下午4:42
 * @version $Id$
 */
public class FindAnagramMappings {

    /**
     * 760. Find Anagram Mappings
     * <p>
     * Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order
     * of the elements in A.
     *
     * We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at
     * index j.
     *
     * These lists A and B may contain duplicates. If there are multiple answers, output any of them.
     * </p>
     *
     * @param A
     * @param B
     * @return
     */
    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return new int[0];
        }
        int[] res = new int[A.length];
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            indexMap.putIfAbsent(B[i], new LinkedList<>());
            indexMap.get(B[i]).add(i);
        }
        for (int i = 0; i < A.length; i++) {
            List<Integer> list = indexMap.get(A[i]);
            res[i] = list.get(0);
            if (list.size() > 1) {
                list.remove(0);
            }
        }
        return res;
    }
}
