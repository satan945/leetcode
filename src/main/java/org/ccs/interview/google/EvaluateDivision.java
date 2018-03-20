/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 399. Evaluate Division
 * 
 * @author Abel created on 2018/3/5 14:02
 * @version $Id$
 */
public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (queries == null || queries.length == 0 || equations == null || equations.length == 0 || values == null
                || values.length == 0 || equations.length != values.length) {
            return new double[0];
        }
        Map<String, List<EvaluateNode>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            map.putIfAbsent(equation[0], new ArrayList<>());
            map.get(equation[0]).add(new EvaluateNode(equation[1], values[i]));
            map.putIfAbsent(equation[1], new ArrayList<>());
            map.get(equation[1]).add(new EvaluateNode(equation[0], 1 / values[i]));
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            res[i] = dfsGetResult(query[0], query[1], map, 1.0, new HashSet<>());
        }
        return res;

    }

    private double dfsGetResult(String start, String end, Map<String, List<EvaluateNode>> map, double val,
            HashSet<String> visited) {
        if (visited.contains(start)) {
            return -1.0;
        }
        if (!map.containsKey(start)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return val;
        }
        visited.add(start);
        for (EvaluateNode node : map.get(start)) {
            double subVal = dfsGetResult(node.token, end, map, val * node.val, visited);
            if (subVal != -1.0) {
                return subVal;
            }
        }
        visited.remove(start);
        return -1;
    }

    class EvaluateNode {
        public String token;
        public double val;

        public EvaluateNode(String token, double val) {
            this.token = token;
            this.val = val;
        }
    }

}
