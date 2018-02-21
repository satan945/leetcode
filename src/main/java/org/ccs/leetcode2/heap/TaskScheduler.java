/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 621. Task Scheduler
 * 
 * @author abel created on 2018/2/7 下午12:58
 * @version $Id$
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        pq.addAll(countMap.entrySet());
        int res = 0;
        while (!pq.isEmpty()) {
            int k = n + 1;
            List<Map.Entry<Character, Integer>> tmpList = new ArrayList<>();
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Character, Integer> top = pq.poll();
                top.setValue(top.getValue() - 1);
                k--;
                tmpList.add(top);
                res++;
            }
            for (Map.Entry<Character, Integer> entry : tmpList) {
                if (entry.getValue() > 0) {
                    pq.offer(entry);
                }
            }
            if (pq.isEmpty()) {
                break;
            }
            res += k;
        }
        return res;

    }
}
