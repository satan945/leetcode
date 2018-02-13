/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.lintcode.contest.mock4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 808. Movie Network
 * 
 * http://www.lintcode.com/en/problem/movie-network/
 * 
 * @author Abel created on 2018/2/12 21:30
 * @version $Id$
 */
public class MovieNetwork {
    public int[] topKMovie(int[] rating, int[][] G, int S, int K) {
        Map<Integer, List<Integer>> relationMap = new HashMap<>();
        Map<Integer, Integer> ratingMap = new HashMap<>();
        for (int i = 0; i < G.length; i++) {
            relationMap.put(i, transferToList(G[i]));
            ratingMap.put(i, rating[i]);
        }
        Set<Integer> allMovies = getAllMovies(S, relationMap);
        allMovies.remove(S);
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry : ratingMap.entrySet()) {
            if (allMovies.contains(entry.getKey())) {
                pq.offer(entry);
            }
        }
        int[] res = new int[K];
        for (int i = 0; i < K && !pq.isEmpty(); i++) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

    private Set<Integer> getAllMovies(int s, Map<Integer, List<Integer>> relationMap) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        Set<Integer> res = new HashSet<>();
        while (!queue.isEmpty()) {
            int movie = queue.poll();
            if (res.contains(movie)) {
                continue;
            }
            res.add(movie);
            queue.addAll(relationMap.get(movie));
        }
        return res;
    }

    private List<Integer> transferToList(int[] relation) {
        List<Integer> list = new ArrayList<>();
        for (int r : relation) {
            list.add(r);
        }
        return list;
    }

    public static void main(String[] args) {
        MovieNetwork solution = new MovieNetwork();
        int[] res = solution.topKMovie(new int[] { 10, 20, 30, 40 }, new int[][] { { 1, 3 }, { 0, 2 }, { 1 }, { 0 } },
                0, 2);
        int[] res1 = solution.topKMovie(new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 },
                new int[][] { { 1, 4, 5 }, { 0, 2, 3 }, { 1, 7 }, { 1, 6, 7 }, { 0 }, { 0 }, { 3 }, { 2, 3 }, {} }, 5,
                3);
        System.out.println(res1);
    }
}
