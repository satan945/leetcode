/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.backtracking.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abel created on 2017/7/14 21:58
 * @version $Id$
 */

public class Solution {
    /**
     * 401. Binary Watch
     * <p>
     * https://leetcode.com/problems/binary-watch
     * <p>
     * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the
     * minutes (0-59).
     *
     * Each LED represents a zero or one, with the least significant bit on the right.
     *
     *
     * For example, the above binary watch reads "3:25".
     *
     * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible
     * times the watch could represent.
     *
     * Example:
     *
     * Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *
     * Note: The order of output does not matter. The hour must not contain a leading zero, for example "01:00" is not
     * valid, it should be "1:00". The minute must be consist of two digits and may contain a leading zero, for example
     * "10:2" is not valid, it should be "10:02".
     * </p>
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        String[][] HOURS = new String[][] { //
                { "0" }, // 0
                { "1", "2", "4", "8" }, // 1
                { "3", "5", "6", "9", "10" }, // 2
                { "7", "11" } };// 3
        String[][] MINUTES = new String[][] { //
                { "00" }, // 0
                { "01", "02", "04", "08", "16", "32" }, // 1
                { "03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48" }, // 2
                { "07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49",
                        "50", "52", "56" }, // 3
                { "15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58" }, // 4
                { "31", "47", "55", "59" } };// 5
        // loop from 0 to 3 which is the max number of bits can be set in hours (4 bits)
        for (int i = 0; i <= num && i <= 3; i++) {
            // this if condition is to make sure the index from minutes array would be valid
            if (num - i <= 5) {
                // if we have i 1's in hours, then we need n - i 1's in minutes, that's why the arrays were created by
                // grouping the number of 1's bits
                for (String str1 : HOURS[i]) {
                    for (String str2 : MINUTES[num - i]) {
                        result.add(str1 + ":" + str2);
                    }
                }
            }
        }
        return result;
    }

    // Normal
    public List<String> readBinaryWatchBackTracking(int num) {
        List<String> res = new ArrayList<>();
        int[] HOURS = new int[] { 1, 2, 4, 8 };
        int[] MINUTES = new int[] { 1, 2, 4, 8, 16, 32 };
        for (int i = 0; i <= num; i++) {
            List<Integer> hourList = generateDigits(HOURS, i);
            List<Integer> minuteList = generateDigits(MINUTES, num - i);
            for (int hour : hourList) {
                if (hour >= 12) {
                    continue;
                }
                for (int min : minuteList) {
                    if (min >= 60) {
                        continue;
                    }
                    res.add(hour + ":" + (min < 10 ? "0" + min : min));
                }
            }
        }
        return res;
    }

    /**
     * 
     * @param values
     * @param count
     * @return
     */
    private List<Integer> generateDigits(int[] values, int count) {
        List<Integer> ret = new ArrayList<>();
        doGenerate(values, count, 0, 0, ret);
        return ret;
    }

    /**
     * 
     * @param values
     * @param count
     * @param pos
     * @param sum
     * @param ret
     */
    private void doGenerate(int[] values, int count, int pos, int sum, List<Integer> ret) {
        if (count == 0) {
            ret.add(sum);
            return;
        }
        for (int i = pos; i < values.length; i++) {
            sum += values[i];
            doGenerate(values, count--, i++, sum, ret);
        }
    }
}
