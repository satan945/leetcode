/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 358. Rearrange String k Distance Apart
 * 
 * @author abel created on 2018/3/5 下午8:50
 * @version $Id$
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            cntMap.put(ch, cntMap.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (o1, o2) -> o1.getValue().equals(o2.getValue()) ? o1.getKey().compareTo(o2.getKey())
                        : o2.getValue() - o1.getValue());
        StringBuilder sb = new StringBuilder();
        pq.addAll(cntMap.entrySet());
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            sb.append(entry.getKey());
            entry.setValue(entry.getValue() - 1);
            waitQueue.offer(entry);
            if (waitQueue.size() < k) {
                continue;
            }
            Map.Entry<Character, Integer> wait = waitQueue.poll();
            if (wait.getValue() > 0) {
                pq.offer(wait);
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        RearrangeStringKDistanceApart rearrangeStringKDistanceApart = new RearrangeStringKDistanceApart();
        System.out.println(rearrangeStringKDistanceApart.rearrangeString("aaabc", 2));
    }
}
