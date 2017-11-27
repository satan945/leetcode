/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.unsorted.medium;

/**
 * @author abel created on 2017/11/25 上午11:45
 * @version $Id$
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.length() == 0) {
            return S;
        }
        String origin = S.replace("-", "");
        if ("".equals(origin)) {
            return "";
        }
        int rest = origin.length() % K;
        StringBuilder sb = new StringBuilder();
        if (rest != 0) {
            sb.append(origin.substring(0, rest)).append("-");
        }
        for (int i = rest != 0 ? rest + 1 : rest; i < origin.length(); i += K) {
            sb.append(origin.substring(i, i + K)).append("-");
        }
        return sb.toString().substring(0, sb.length() - 1).toUpperCase();
    }
}
