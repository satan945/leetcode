/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 675. Cut Off Trees for Golf Event
 * 
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 * 
 * @author abel created on 2018/3/7 下午5:23
 * @version $Id$
 */
public class CutOffTreesForGolfEvent {

    private final int[] moves = new int[] { 1, 0, -1, 0, 1 };

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        Cell cur = new Cell(0, 0, forest.get(0).get(0));
        PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2) -> (o1.h - o2.h));
        for (int i = 0; i < forest.size(); i++) {
            List<Integer> line = forest.get(i);
            for (int j = 0; j < line.size(); j++) {
                if (line.get(j) > 1) {
                    pq.offer(new Cell(j, i, line.get(j)));
                }
            }
        }
        int res = 0;
        while (!pq.isEmpty()) {
            Cell dest = pq.poll();
            int distance = calDis(cur, dest, forest);
            if (distance == -1) {
                return -1;
            } else {
                res += distance;
                cur = dest;
            }
        }
        return res;
    }

    private int calDis(Cell start, Cell dest, List<List<Integer>> forest) {
        int dis = 0;
        Queue<Cell> queue = new LinkedList<>();
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell cell = queue.poll();
                int x = cell.x;
                int y = cell.y;
                if (cell.equals(dest)) {
                    return dis;
                }
                for (int j = 0; j < moves.length - 1; j++) {
                    int newY = y + moves[j];
                    int newX = x + moves[j + 1];
                    if (canMove(newX, newY, forest, visited)) {
                        queue.offer(new Cell(newX, newY, forest.get(newY).get(newX)));
                        visited[newY][newX] = true;
                    }
                }
            }
            dis++;
        }
        return -1;
    }

    private boolean canMove(int newX, int newY, List<List<Integer>> forest, boolean[][] visited) {
        int m = forest.size();
        int n = forest.get(0).size();
        if (newX < 0 || newY < 0 || newX >= n || newY >= m || forest.get(newY).get(newX) <= 0 || visited[newY][newX]) {
            return false;
        }
        return true;
    }

    class Cell {
        public int x;
        public int y;
        public int h;

        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        List<List<Integer>> forest2 = new ArrayList<>();

        forest.add(Arrays.asList(1, 2, 3));
        forest.add(Arrays.asList(0, 0, 4));
        forest.add(Arrays.asList(7, 6, 5));
        forest2.add(Arrays.asList(1, 2, 3));
        forest2.add(Arrays.asList(0, 0, 0));
        forest2.add(Arrays.asList(7, 6, 5));

        CutOffTreesForGolfEvent cutOffTreesForGolfEvent = new CutOffTreesForGolfEvent();
        // System.out.println(cutOffTreesForGolfEvent.cutOffTree(forest));
        System.out.println(cutOffTreesForGolfEvent.cutOffTree(forest2));

    }

}
