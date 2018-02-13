/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.cracking189.arraysandstrings;

/**
 * Check two strings if one is a permutation of the other
 * 
 * @author Abel created on 2018/2/7 21:31
 * @version $Id$
 */
public class CheckPermutation {

    public boolean checkPermutation(String a, String b) {
        int aLen = a == null ? 0 : a.length();
        int bLen = b == null ? 0 : b.length();
        if (aLen != bLen) {
            return false;
        }
        int[] aCount = new int[128];
        int[] bCount = new int[128];
        for (int i = 0; i < aLen; i++) {
            int val = a.charAt(i);
            aCount[val]++;
        }
        for (int j = 0; j < bLen; j++) {
            int val = b.charAt(j);
            bCount[val]++;
        }
        for (int i = 0; i < 128; i++) {
            if (aCount[i] != bCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckPermutation checkPermutation = new CheckPermutation();
        System.out.println(checkPermutation.checkPermutation("abc","bca"));
        System.out.println(checkPermutation.checkPermutation("abc","bbb"));
    }
}
