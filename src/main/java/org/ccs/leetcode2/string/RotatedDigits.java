/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 788. Rotated Digits
 * 
 * https://leetcode.com/problems/rotated-digits
 * 
 * @author abel created on 2018/3/2 下午4:20
 * @version $Id$
 */
public class RotatedDigits {

    private Set<Integer> validNum = new HashSet<>();
    private Set<Integer> notValidNum = new HashSet<>();

    public int rotatedDigits(int N) {
        if (N <= 0) {
            return 0;
        }
        int res = 0;
        validNum.addAll(Arrays.asList(2, 5, 6, 9));
        notValidNum.addAll(Arrays.asList(3, 4, 7));
        for (int i = 1; i <= N; i++) {
            res += isValid(i);
        }
        return res;
    }

    private int isValid(int i) {
        boolean found = false;
        while (i > 0) {
            int lastDigit = i % 10;
            if (validNum.contains(lastDigit)) {
                found = true;
            }
            if (notValidNum.contains(lastDigit)) {
                return 0;
            }
            i /= 10;
        }
        return found ? 1 : 0;
    }

    public static void main(String[] args) {
        RotatedDigits rotatedDigits = new RotatedDigits();
        System.out.println(rotatedDigits.rotatedDigits(10));
    }
}
