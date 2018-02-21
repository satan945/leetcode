/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * @author abel created on 2018/2/6 下午12:52
 * @version $Id$
 */
public class BackSpaceCompare {

    public boolean isEqual(char[] a, char[] b) {
        int i = a.length - 1, j = b.length - 1, aCnt = 0, bCnt = 0;
        while (i >= 0 && j >= 0) {
            if (a[i] != '\b' && b[j] != '\b' && a[i] != b[j]) {
                return false;
            }
            while (i >= 0 && j >= 0 && a[i] == b[j]) {
                i--;
                j--;
            }
            while (i >= 0 && a[i] == '\b') {
                i--;
                aCnt++;
            }
            while (j >= 0 && b[j] == '\b') {
                j--;
                bCnt++;
            }
            while (aCnt > 0) {
                i--;
                aCnt--;
            }
            while (bCnt > 0) {
                j--;
                bCnt--;
            }
        }
        return i < 0 && j < 0;
    }

    public static void main(String[] args) {
        String a = "ab\bdc";
        String b = "bdc";
        BackSpaceCompare solution = new BackSpaceCompare();
        System.out.println(solution.isEqual(a.toCharArray(), b.toCharArray()));
    }
}
