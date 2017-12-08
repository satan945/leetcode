/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bfs.medium;

import org.ccs.leetcode.bean.TreeNode;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author abel created on 2017/10/9 下午5:48
 * @version $Id$
 */
public class Solution {
    /**
     * 199. Binary Tree Right Side View
     * <p>
     * https://leetcode.com/problems/binary-tree-right-side-view
     * <p>
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can
     * see ordered from top to bottom.
     *
     * org.ccs.leetcode.dfs.medium.Solution.rightSideView()
     *
     * org.ccs.leetcode.bfs.medium.Solution.rightSideView()
     *
     * For example: Given the following binary tree, 1 <--- / \ 2 3 <--- \ \ 5 4 <---
     * </p>
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        while (size > 0) {
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
            size = queue.size();
        }
        return res;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        // reverse level traversal
        List<Integer> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0)
                    result.add(cur.val);
                if (cur.right != null)
                    queue.offer(cur.right);
                if (cur.left != null)
                    queue.offer(cur.left);
            }
        }
        return result;
    }

    /**
     * 127. Word Ladder
     * <p>
     * https://leetcode.com/problems/word-ladder
     * <p>
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
     * sequence from beginWord to endWord, such that:
     * 
     * Only one letter can be changed at a time. Each transformed word must exist in the word list. Note that beginWord
     * is not a transformed word. For example,
     * 
     * Given: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log","cog"] As one shortest
     * transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
     * 
     * Note: Return 0 if there is no such transformation sequence. All words have the same length. All words contain
     * only lowercase alphabetic characters. You may assume no duplicates in the word list. You may assume beginWord and
     * endWord are non-empty and are not the same.
     * </p>
     * 
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        int res = 1;
        while (!reached.contains(endWord)) {
            HashSet<String> toAdd = new HashSet<>();
            for (String str : reached) {
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String s = new String(chars);
                        if (wordList.contains(s)) {
                            toAdd.add(s);
                            wordList.remove(s);
                        }
                    }
                }
            }
            res++;
            if (toAdd.size() == 0) {
                return 0;
            }
            reached = toAdd;
        }
        return res;
    }

    /**
     * 210. Course Schedule II
     * <p>
     * https://leetcode.com/problems/course-schedule-ii
     * <p>
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
     * expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
     * take to finish all courses.
     *
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
     * courses, return an empty array.
     *
     * For example:
     *
     * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the
     * correct course order is [0,1]
     *
     * 4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take course 3 you should have finished
     * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course
     * order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
     * </p>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degrees = new int[numCourses];
        List<List<Integer>> courseList = new ArrayList<>();
        initGraph(degrees, courseList, prerequisites);
        return bfsFindOrder(numCourses, courseList, degrees);
    }

    private int[] bfsFindOrder(int numCourses, List<List<Integer>> courseList, int[] degrees) {
        int[] res = new int[numCourses];
        Queue<Integer> toBeAccessed = new LinkedList<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                toBeAccessed.offer(i);
            }
        }
        int order = 0;
        while (!toBeAccessed.isEmpty()) {
            int cur = toBeAccessed.poll();
            res[order] = cur;
            order++;
            List<Integer> list = courseList.get(cur);
            for (int course : list) {
                degrees[course]--;
                if (degrees[course] == 0) {
                    toBeAccessed.offer(course);
                }
            }
        }
        return order == numCourses ? res : new int[0];
    }

    private void initGraph(int[] degrees, List<List<Integer>> courseList, int[][] prerequisites) {
        int n = degrees.length;
        while (n-- > 0) {
            courseList.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            degrees[pre[0]]++;
            courseList.get(pre[1]).add(pre[0]);
        }
    }

    /**
     * 542. 01 Matrix
     *
     * <p>
     * https://leetcode.com/problems/01-matrix
     * <p>
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     * </p>
     * https://discuss.leetcode.com/topic/92578/java-dfs-solution-beat-95
     * 
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {// dp solution
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int MAX = m + n;
        int[][] res = new int[m][n];
        for (int[] row : res) {
            Arrays.fill(row, MAX);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    int left = j == 0 ? MAX : res[i][j - 1];
                    int up = i == 0 ? MAX : res[i - 1][j];
                    res[i][j] = Math.min(left, up) + 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int right = j == n - 1 ? MAX : res[i][j + 1];
                int down = i == m - 1 ? MAX : res[i + 1][j];
                res[i][j] = Math.min(res[i][j], Math.min(right, down) + 1);
            }
        }

        return res;
    }

    /**
     * 
     * 286. Walls and Gates
     * <p>
     * https://leetcode.com/problems/walls-and-gates
     * <p>
     * You are given a m x n 2D grid initialized with these three possible values.
     * 
     * -1 - A wall or an obstacle. 0 - A gate. INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647
     * to represent INF as you may assume that the distance to a gate is less than 2147483647. Fill each empty room with
     * the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     * 
     * </p>
     * 
     * @param rooms
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if (rooms[i][j] == 0) {
                    bfsCalDis(rooms, i, j);
                }
            }
        }
    }

    private void bfsCalDis(int[][] rooms, int i, int j) {
        int[] move = new int[] { 1, 0, -1, 0, 1 };
        Queue<int[]> queue = new LinkedList<>();
        int distance = 0;
        queue.offer(new int[] { i, j });
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] pos = queue.poll();
                int y = pos[0];
                int x = pos[1];
                rooms[y][x] = distance;
                for (int m = 0; m < move.length - 1; m++) {
                    if (canMove(rooms, y + move[m], x + move[m + 1], distance)) {
                        queue.offer(new int[] { y + move[m], x + move[m + 1] });
                    }
                }
            }
            distance++;
        }
    }

    private boolean canMove(int[][] rooms, int nY, int nX, int distance) {
        int m = rooms.length;
        int n = rooms[0].length;
        if (nY < 0 || nY > m - 1 || nX < 0 || nX > n - 1 || rooms[nY][nX] == -1 || rooms[nY][nX] <= distance) {
            return false;
        }
        return true;
    }

    /**
     * 417. Pacific Atlantic Water Flow
     * <p>
     * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the
     * "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and
     * bottom edges.
     * 
     * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or
     * lower.
     * 
     * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
     * 
     * Note: The order of returned grid coordinates does not matter. Both m and n are less than 150.
     * </p>
     * 
     * @param matrix
     * @return
     */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pReach = new boolean[m][n];
        boolean[][] aReach = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pQueue.offer(new int[] { i, 0 });
            aQueue.offer(new int[] { i, n - 1 });
            pReach[i][0] = true;
            aReach[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pQueue.offer(new int[] { 0, j });
            aQueue.offer(new int[] { m - 1, j });
            pReach[0][j] = true;
            aReach[m - 1][j] = true;
        }
        bfsReachOcean(pQueue, pReach, matrix);
        bfsReachOcean(aQueue, aReach, matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pReach[i][j] && aReach[i][j]) {
                    res.add(new int[] { i, j });
                }
            }
        }
        return res;
    }

    private void bfsReachOcean(Queue<int[]> queue, boolean[][] reach, int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] move = new int[] { 1, 0, -1, 0, 1 };
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < move.length - 1; i++) {
                int newY = pos[0] + move[i];
                int newX = pos[1] + move[i + 1];
                if (newY < 0 || newY > m - 1 || newX < 0 || newX > n - 1 || matrix[newY][newX] > matrix[pos[0]][pos[1]]
                        || reach[newY][newX]) {
                    continue;
                }
                reach[newY][newX] = true;
                queue.offer(new int[] { newY, newX });
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Integer a = 0;
        System.out.println(a);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<String> stringList = new ArrayList<>();
        stringList.add("hot");
        stringList.add("dot");
        stringList.add("dog");
        stringList.add("lot");
        stringList.add("log");
        stringList.add("cog");
        // System.out.println(new Solution().rightSideView(root));
        // System.out.println(new Solution().ladderLength("hit", "cog", stringList));
        int[][] matrix = { { 1, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 0, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 0, 1, 1, 1, 1 } };

        solution.updateMatrix(matrix);
    }
}
