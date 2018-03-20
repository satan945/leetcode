/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.ms.ota;

/**
 * @author Abel created on 2018/3/6 17:31
 * @version $Id$
 */
public class StringEncodeAndDecode {
    /**
     * "aaaabbbbcccd" to "a4b4c3d1"
     * 
     * @param s
     * @return
     */
    public String encode(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i));
                count = 1;
            }
        }
        sb.append(count);
        return sb.toString();
    }

    /**
     * "a4b4c3d1" to "aaaabbbbcccd"
     */
    public String decode(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        if (!Character.isLetter(s.charAt(0))) {
            return s;
        }
        int count = 0;
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                if (count > 0) {
                    appendDuplicateChars(sb, count);
                }
                sb.append(ch);
                count = 0;
            } else if (Character.isDigit(ch)) {
                count *= 10;
                count += (ch - '0');
            }
        }
        appendDuplicateChars(sb, count);
        return sb.toString();
    }

    private void appendDuplicateChars(StringBuilder sb, int count) {
        char ch = sb.charAt(sb.length() - 1);
        for (int i = 1; i < count; i++) {
            sb.append(ch);
        }
    }

    public static void main(String[] args) {
        String a = "aaaabbbbcccd";
        String b = "a14b4c3d1";
        System.out.println(new StringEncodeAndDecode().encode(a));
        System.out.println(new StringEncodeAndDecode().decode(b));
    }
}
