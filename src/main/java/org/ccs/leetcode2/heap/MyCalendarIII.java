/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.heap;

import java.util.PriorityQueue;

/**
 * @author abel created on 2018/2/26 下午7:18
 * @version $Id$
 */
public class MyCalendarIII {

    private PriorityQueue<int[]> queue;

    public MyCalendarIII() {
        queue = new PriorityQueue<>(((o1, o2) -> (o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0])));

    }

    public int book(int start, int end) {
        if (queue.isEmpty()) {
            queue.add(new int[] { start, end });
        } else {
            int[] pre = queue.poll();
            if (start > pre[1]) {
                pre[1] = end;
            } else {
                queue.offer(new int[] { start, end });
            }
            queue.offer(pre);
        }
        return queue.size();

    }
}
