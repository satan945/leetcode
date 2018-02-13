/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.cracking189.arraysandstrings;

/**
 * Page 91
 * 
 * @author Abel created on 2018/2/7 21:51
 * @version $Id$
 */
public class StringCompression {

    public String compressString(String input) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                sb.append(input.charAt(i));
                continue;
            }
            if (input.charAt(i) != input.charAt(i-1)) {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(input.charAt(i));
                count = 1;
            } else {
                count++;
            }
            if (i == input.length() - 1) {
                if (count > 1) {
                    sb.append(count);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringCompression stringCompression = new StringCompression();
        System.out.println(stringCompression.compressString("aabccccca"));
    }
}
