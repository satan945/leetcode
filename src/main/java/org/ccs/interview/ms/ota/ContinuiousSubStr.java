/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.ms.ota;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Abel created on 2018/3/6 17:47
 * @version $Id$
 */
public class ContinuiousSubStr {

    public List<String> getContinuiousStrs(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int i = 0, j = 1;
        while (i < str.length() && j < str.length()) {
            char ch1 = str.charAt(j - 1);
            char ch2 = str.charAt(j);
            if (ch2 - ch1 == 1) {
                String sub = str.substring(i, j + 1);
                if (!res.contains(sub)) {
                    res.add(sub);
                }
                j++;
            } else {
                i = j;
                j = i + 1;
            }
        }
        return res;
    }

    private void addNewResult(List<String> res, char ch1, char ch2) {
        List<String> tmp = new ArrayList<>();
        String sub = "" + ch1 + ch2;
        if (!res.contains(sub)) {
            res.add(sub);
        }
        for (String str : res) {
            if (str.charAt(str.length() - 1) == ch1) {
                tmp.add(str + ch2);
            }
        }
        res.addAll(tmp);
    }

    public static void main(String[] args) {
        String a = "BCCDDEF";
        System.out.println(new ContinuiousSubStr().getContinuiousStrs(a));
        String[] aaa = "/home/a/b".split("/");
        System.out.println(aaa);
    }
}
