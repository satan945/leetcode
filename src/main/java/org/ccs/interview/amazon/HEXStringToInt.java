/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 转化HEX string to Int
 * 
 * @author abel created on 2018/2/5 下午7:18
 * @version $Id$
 */
public class HEXStringToInt {

    public int hexToInt(String hexString) {
        int res = 0;
        hexString = hexString.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        int pow = 0;
        for (int i = hexString.length() - 1; i >= 0; i--) {
            char ch = hexString.charAt(i);
            if (Character.isDigit(ch)) {
                res += (ch - '0') * Math.pow(16, pow);
            } else {
                Integer num = map.get(ch);
                if (num == null) {
                    return -1;
                }
                res += num * Math.pow(16, pow);
            }
            pow++;
        }
        return res;
    }

    public int hexToInt2(String hexString) {
        int res = 0;
        hexString = hexString.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        for (int i = 0; i < hexString.length(); i++) {
            res *= 16;
            char ch = hexString.charAt(i);
            if (Character.isDigit(ch)) {
                res += ch - '0';
            } else {
                res += map.get(ch);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        HEXStringToInt hexStringToInt = new HEXStringToInt();
        System.out.println(hexStringToInt.hexToInt("FFFF"));
        System.out.println(hexStringToInt.hexToInt2("FFFF"));
    }
}
