/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.ms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * OTS02
 * 
 * @author abel created on 2018/1/17 上午11:22
 * @version $Id$
 */
public class GetPreCourse {

    public int[] getCourseOrder(int[][] courseInfo, int courseToTake) {
        Map<Integer, int[]> parentMap = new HashMap<>();
        for (int i = 0; i < courseInfo.length; i++) {
            parentMap.put(i, courseInfo[i]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(courseToTake);
        List<Integer> res = new LinkedList<>();
        res.add(courseToTake);
        while (!queue.isEmpty()) {
            int course = queue.poll();
        }
        return new int[1];
    }

}
