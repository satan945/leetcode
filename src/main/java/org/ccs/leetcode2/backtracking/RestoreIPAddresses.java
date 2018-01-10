/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * <p>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * </p>
 * 
 * @author abel created on 2018/1/4 下午6:24
 * @version $Id$
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (s.length() > 12 || s.length() < 4) {
            return new ArrayList<>();
        }
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (isValidIP(s1) && isValidIP(s2) && isValidIP(s3) && isValidIP(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValidIP(String ip) {
        if (ip == null || ip.length() == 0 || ip.length() > 3 || (ip.length() > 1 && ip.charAt(0) == '0')
                || Integer.valueOf(ip) > 255) {
            return false;
        }
        return true;
    }
}
