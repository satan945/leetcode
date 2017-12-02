/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the
 * following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded
 * decimal numbers.
 * 
 * Design a log storage system to implement the following functions:
 * 
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 * 
 * 
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the
 * range from start to end. Start and end all have the same format as timestamp. However, granularity means the time
 * level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity =
 * "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 * </p>
 * 
 * @author abel created on 2017/11/29 下午5:42
 * @version $Id$
 */
public class LogSystem {
    private List<String[]> logs;
    private List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
    private int[] indexes = new int[] { 4, 7, 10, 13, 16, 19 };

    public LogSystem() {
        logs = new ArrayList<>();
    }

    public void put(int id, String timestamp) {
        logs.add(new String[] { String.valueOf(id), timestamp });
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int index = indexes[units.indexOf(gra)];
        List<Integer> res = new ArrayList<>();
        for (String[] log : logs) {
            if (log[1].substring(0, index).compareTo(s.substring(0, index)) >= 0
                    && log[1].substring(0, index).compareTo(e.substring(0, index)) <= 0) {
                res.add(Integer.parseInt(log[0]));
            }
        }
        return res;
    }
}
