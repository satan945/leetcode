/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest68;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 767. Reorganize String
 * 
 * @author abel created on 2018/1/20 下午6:50
 * @version $Id$
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        Map<Character, Integer> countMap = new TreeMap<>();
        char[] chars = S.toCharArray();
        int len = S.length();
        for (char ch : chars) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            if (len % 2 == 1 && countMap.get(ch) > (S.length() / 2) + 1) {
                return "";
            }
            if (len % 2 == 0 && countMap.get(ch) > (S.length() / 2)) {
                return "";
            }
        }
        Queue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        queue.addAll(countMap.entrySet());
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry1 = queue.poll();
            if (res.length() > 0 && res.charAt(res.length() - 1) == entry1.getKey()) {
                Map.Entry<Character, Integer> entry2 = queue.poll();
                queue.offer(entry1);
                res.append(entry2.getKey());
                int count = entry2.getValue();
                if (count - 1 > 0) {
                    entry2.setValue(count - 1);
                    queue.offer(entry2);
                }
            } else {
                res.append(entry1.getKey());
                int count = entry1.getValue();
                if (count - 1 > 0) {
                    entry1.setValue(count - 1);
                    queue.offer(entry1);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        System.out.println(reorganizeString.reorganizeString("aaab"));
    }
}
