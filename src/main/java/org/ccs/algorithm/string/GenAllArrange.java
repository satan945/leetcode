/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abel created on 2017/9/6 15:47
 * @version $Id$
 */
public class GenAllArrange {

    /**
     * Generate all arrangements of a String only contains a-z
     * 
     * 
     * @param s
     * @return
     */
    public List<String> genAllArrangeRecur1(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        genAll("", s, res);
        return res;
    }

    private void genAll(String pre, String rest, List<String> res) {
        if (rest.length() == 0) {
            res.add(pre);
            return;
        }
        for (int i = 0; i < rest.length(); i++) {
            genAll(pre + rest.charAt(i), rest.substring(0, i) + rest.substring(i + 1, rest.length()), res);
        }
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        GenAllArrange genAllArrange = new GenAllArrange();
        System.out.println(genAllArrange.genAllArrangeRecur1("123"));
    }
}
