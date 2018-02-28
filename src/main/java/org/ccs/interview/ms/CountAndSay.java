/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms;

/**
 * 38. Count and Say
 * 
 * https://leetcode.com/problems/count-and-say
 * 
 * @author abel created on 2018/2/26 下午11:10
 * @version $Id$
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String str = "1";
        StringBuilder sb = new StringBuilder();
        if (n == 1) {
            return str;
        }
        for (int i = 2; i <= n; i++) {
            char[] seq = str.toCharArray();
            int count = 1;
            for (int j = 1; j < seq.length; j++) {
                if (seq[j] == seq[j - 1]) {
                    count++;
                } else {
                    sb.append(count).append(seq[j - 1]);
                    count = 1;
                }
            }
            sb.append(count).append(seq[seq.length - 1]);
            str = sb.toString();
            sb.delete(0, sb.length());
        }
        return str;

    }


    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(6));
    }
}
