/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * @author abel created on 2018/2/6 下午12:52
 * @version $Id$
 */
public class BackSpaceAndCapsLockCompare {

    public boolean isEqual(char[] a, char[] b) {
        int i = a.length - 1, j = b.length - 1, aCnt = 0, bCnt = 0;
          return i < 0 && j < 0;
    }

    public static void main(String[] args) {
        String a = "ab\bdc";
        String b = "bdc";
        BackSpaceAndCapsLockCompare solution = new BackSpaceAndCapsLockCompare();
        System.out.println(solution.isEqual(a.toCharArray(), b.toCharArray()));
    }
}
