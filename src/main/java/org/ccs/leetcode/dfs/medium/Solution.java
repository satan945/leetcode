/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.ccs.leetcode.bean.ListNode;
import org.ccs.leetcode.bean.NestedInteger;
import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/9/1 15:06
 * @version $Id$
 */
public class Solution {

    /**
     * 200. Number of Islands
     * <p>
     * https://leetcode.com/problems/number-of-islands
     * <p>
     *
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the
     * grid are all surrounded by water.
     *
     * Example 1:
     *
     * 11110
     *
     * 11010
     *
     * 11000
     *
     * 00000
     *
     * Answer: 1
     *
     * Example 2:
     *
     * 11000
     *
     * 11000
     *
     * 00100
     *
     * 00011
     *
     * Answer: 3
     *
     * Credits: Special thanks to @mithmatt for adding this problem and creating all test cases.
     * </p>
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfsMark(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfsMark(char[][] grid, int i, int j) {
        int[][] move = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i > m || j > n || grid[i][j] != '1') {
            return;
        }
        for (int k = 0; k < move.length; k++) {
            grid[i][j] = 0;
            dfsMark(grid, move[k][0], move[k][1]);
        }
    }

    /**
     * 130. Surrounded Regions
     * <p>
     * https://leetcode.com/problems/surrounded-regions
     * <p>
     *
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     *
     * For example,
     *
     * X X X X
     *
     * X O O X
     *
     * X X O X
     *
     * X O X X
     *
     * After running your function, the board should be:
     *
     * X X X X
     *
     * X X X X
     *
     * X X X X
     *
     * X O X X
     * </p>
     *
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int m = board.length, n = board[0].length;
        // start from first and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfsBoundry(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfsBoundry(board, i, n - 1);
            }
        }
        // start from first and last row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfsBoundry(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfsBoundry(board, m - 1, j);
            }
        }

        // last loop to change O to X
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfsBoundry(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || j > 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '*';
        }
        if (i > 1 && board[i - 1][j] == 'O') {
            dfsBoundry(board, i - 1, j);
        }
        if (i < m - 2 && board[i + 1][j] == 'O') {
            dfsBoundry(board, i + 1, j);
        }
        if (j > 1 && board[i][j - 1] == 'O') {
            dfsBoundry(board, i, j - 1);
        }
        if (j < n - 2 && board[i][j + 1] == 'O') {
            dfsBoundry(board, i, j + 1);
        }
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * <p>
     * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree
     * <p>
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * </p>
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) {
            return null;
        }
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildBST(head, slow);
        root.right = buildBST(slow.next, tail);
        return root;
    }

    /**
     * 542. 01 Matrix
     * <p>
     * https://leetcode.com/problems/01-matrix
     * <p>
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     * Example 1:
     *
     * Input:
     *
     * 0 0 0
     *
     * 0 1 0
     *
     * 0 0 0
     *
     * Output:
     *
     * 0 0 0
     *
     * 0 1 0
     *
     * 0 0 0
     *
     * Example 2: Input:
     *
     * 0 0 0
     *
     * 0 1 0
     *
     * 1 1 1
     *
     * Output: 0 0 0
     *
     * 0 1 0
     *
     * 1 2 1
     *
     * Note: The number of elements of the given matrix will not exceed 10,000. There are at least one 0 in the given
     * matrix. The cells are adjacent in only four directions: up, down, left and right.
     * </p>
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                }

            }
        }
        return res;
    }

    /**
     * 364. Nested List Weight Sum II
     * <p>
     * https://leetcode.com/problems/nested-list-weight-sum-ii
     * <p>
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
     *
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     *
     * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
     * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
     *
     * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
     *
     * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3
     * + 4*2 + 6*1 = 17)
     * </p>
     *
     * @param nestedList
     * @return
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int maxLevel = calLevel(nestedList);
        return calDepthSum(nestedList, maxLevel);
    }

    private int calDepthSum(List<NestedInteger> nestedList, int level) {
        int res = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                res += nestedInteger.getInteger() * level;
            } else {
                res += calDepthSum(nestedInteger.getList(), level - 1);
            }
        }
        return res;
    }

    private int calLevel(List<NestedInteger> nestedList) {
        int maxLevel = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                maxLevel = Math.max(maxLevel, 1);
            } else {
                maxLevel = Math.max(maxLevel, 1 + calLevel(nestedInteger.getList()));
            }
        }
        return maxLevel;
    }

    /**
     * 207. Course Schedule
     * <p>
     * https://leetcode.com/problems/course-schedule
     * <p>
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
     * expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
     *
     * For example:
     *
     * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is
     * possible.
     *
     * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and
     * to take course 0 you should also have finished course 1. So it is impossible.
     * </p>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                count++;
            }
        }
        while (queue.size() > 0) {
            int course = queue.poll();
            for (int i = 0; i < graph[course].size(); i++) {
                int pointer = (int) graph[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                    count++;
                }
            }
        }
        return count == numCourses;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visted = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfsSchedule(graph, visted, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfsSchedule(ArrayList<Integer>[] graph, boolean[] visted, int courseNum) {
        if (visted[courseNum]) {
            return false;
        } else {
            visted[courseNum] = true;
        }

        for (int i = 0; i < graph[courseNum].size(); i++) {
            if (!dfsSchedule(graph, visted, graph[courseNum].get(i))) {
                return false;
            }
        }
        visted[courseNum] = false;
        return true;
    }

    /**
     * 394. Decode String
     * <p>
     * https://leetcode.com/problems/decode-string
     * <p>
     * Given an encoded string, return it's decoded string.
     *
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
     * exactly k times. Note that k is guaranteed to be a positive integer.
     *
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed,
     * etc.
     *
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
     * repeat numbers, k. For example, there won't be input like 3a or 2[4].
     *
     * Examples:
     *
     * s = "3[a]2[bc]", return "aaabcbc". s = "3[a2[c]]", return "accaccacc". s = "2[abc]3[cd]ef", return
     * "abcabccdcdcdef".
     * </p>
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<Integer> countStack = new Stack<>();
        Stack<String> seqStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                count = count * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                countStack.push(count);
                seqStack.push("");
                count = 0;
            } else if (s.charAt(i) == ']') {
                String seq = seqStack.pop();
                int repeat = countStack.pop();
                StringBuilder rpSb = new StringBuilder();
                for (int j = 0; j < repeat; j++) {
                    rpSb.append(seq);
                }
                if (!seqStack.empty()) {
                    String preString = seqStack.pop();
                    preString += rpSb.toString();
                    seqStack.push(preString);
                } else {
                    sb.append(rpSb.toString());
                }
            } else {
                if (seqStack.empty()) {
                    sb.append(s.charAt(i));
                } else {
                    String pre = seqStack.pop();
                    pre += s.charAt(i);
                    seqStack.push(pre);
                }
            }
        }
        return sb.toString();
    }

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
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    /**
     * 113. Path Sum II
     * <p>
     * https://leetcode.com/problems/path-sum-ii
     * <p>
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     *
     * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 return [
     * [5,4,11,2], [5,8,4,5] ]
     * </p>
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        calPathSum(root, sum, res, new ArrayList<>());
        return res;
    }

    private void calPathSum(TreeNode node, int target, List<List<Integer>> res, ArrayList<Integer> list) {
        if (node == null || target < 0) {
            return;
        }
        list.add(node.val);
        if (node.left == null && node.right == null && target == node.val) {
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);// don't forget to remove the last integer
            return;
        } else {
            calPathSum(node.left, target - node.val, res, list);
            calPathSum(node.right, target - node.val, res, list);
        }
        list.remove(list.size() - 1);
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
     *
     * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
     * how a graph is represented. You may assume that there are no duplicate edges in the input prerequisites.
     * </p>
     * todo
     *
     * https://discuss.leetcode.com/topic/13873/two-ac-solution-in-java-using-bfs-and-dfs-with-explanation
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        ArrayList[] adjs = new ArrayList[numCourses];
        initGraph(prerequisites, incLinkCounts, adjs);
        return findOrderByDFS(adjs);
    }

    private int[] findOrderByDFS(ArrayList[] adjs) {
        return new int[0];
    }

    private void initGraph(int[][] edges, int[] incLinkCounts, ArrayList[] adjs) {
        for (int i = 0; i < adjs.length; i++) {
            adjs[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            incLinkCounts[edge[0]]++;
            adjs[edge[1]].add(edge[0]);
        }
    }

    /**
     * 531. Lonely Pixel I
     * <p>
     * https://leetcode.com/problems/lonely-pixel-i
     * <p>
     * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
     *
     * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels
     * respectively.
     *
     * A black lonely pixel is character 'B' that located at a specific position where the same row and same column
     * don't have any other black pixels.
     *
     * Example: Input: [['W', 'W', 'B'], ['W', 'B', 'W'], ['B', 'W', 'W']]
     *
     * Output: 3 Explanation: All the three 'B's are black lonely pixels.
     * </p>
     *
     * @param picture
     * @return
     */
    public int findLonelyPixel(char[][] picture) {
        int res = 0;
        if (picture == null || picture.length == 0 || picture[0].length == 0) {
            return res;
        }
        int m = picture.length;
        int n = picture[0].length;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 490. The Maze
     *
     * <p>
     * https://leetcode.com/problems/the-maze
     * <p>
     * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
     * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next
     * direction.
     *
     * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the
     * destination.
     *
     * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that
     * the borders of the maze are all walls. The start and destination coordinates are represented by row and column
     * indexes.
     *
     * There is only one ball and one destination in the maze.
     *
     * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
     *
     * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
     * border of the maze are all walls.
     *
     * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
     *
     * </p>
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfsHasPath(maze, start[0], start[1], destination, visited);
    }

    private boolean dfsHasPath(int[][] maze, int i, int j, int[] destination, boolean[][] visited) {
        int[][] moves = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        if (visited[i][j]) {
            return false;
        }
        if (i == destination[0] && j == destination[1]) {
            return true;
        }
        visited[i][j] = true;

        for (int[] move : moves) {
            int x = i;
            int y = j;
            while (canRoll(maze, x + move[0], y + move[1])) {
                x += move[0];
                y += move[1];
            }
            if (dfsHasPath(maze, x, y, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean canRoll(int[][] maze, int row, int col) {
        if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) {
            return false; // stop at borders
        }
        return maze[row][col] != 1; // stop at walls (1 -> wall)
    }

    /**
     * 505. The Maze II
     * <p>
     * https://leetcode.com/problems/the-maze-ii
     * <p>
     *
     * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
     * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next
     * direction.
     *
     * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at
     * the destination. The distance is defined by the number of empty spaces traveled by the ball from the start
     * position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
     *
     * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that
     * the borders of the maze are all walls. The start and destination coordinates are represented by row and column
     * indexes.
     *
     * Note:
     *
     * There is only one ball and one destination in the maze. Both the ball and the destination exist on an empty
     * space, and they will not be at the same position initially. The given maze does not contain border (like the red
     * rectangle in the example pictures), but you could assume the border of the maze are all walls. The maze contains
     * at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
     * </p>
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        return 0;
    }

    /**
     * 576. Out of Boundary Paths
     * <p>
     * https://leetcode.com/problems/out-of-boundary-paths
     * <p>
     * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to
     * adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most
     * move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large,
     * return it after mod 109 + 7.
     *
     * Note: Once you move the ball out of boundary, you cannot move it back. The length and height of the grid is in
     * range [1,50]. N is in range [0,50].
     * </p>
     * ********************** TLE ***************************
     * 
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */

    int paths = 0;
    int[] moves = new int[] { 1, 0, -1, 0, 1 };

    public int findPaths2(int m, int n, int N, int i, int j) {
        dfsFindPaths(m, n, N, i, j);
        return paths;
    }

    private void dfsFindPaths(int m, int n, int move, int y, int x) {
        if (move >= 0 && (y >= m || y < 0 || x >= n || x < 0)) {
            paths++;
            return;
        }
        if (move < 0) {
            return;
        }
        for (int i = 0; i < moves.length - 1; i++) {
            dfsFindPaths(m, n, move - 1, y + moves[i], x + moves[i + 1]);
        }
    }

    /**
     * 547. Friend Circles
     * <p>
     * https://leetcode.com/problems/friend-circles
     * <p>
     * 
     * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
     * nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of
     * C. And we defined a friend circle is a group of students who are direct or indirect friends.
     * 
     * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the
     * ith and jth students are direct friends with each other, otherwise not. And you have to output the total number
     * of friend circles among all the students.
     * 
     * Note: N is in range [1,200]. M[i][i] = 1 for all students. If M[i][j] = 1, then M[j][i] = 1.
     * 
     * </p>
     * 
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfsVisit(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfsVisit(int[][] m, int i, boolean[] visited) {
        for (int j = 0; j < m.length; j++) {
            if (m[i][j] == 1) {
                visited[j] = true;
                dfsVisit(m, j, visited);
            }
        }
    }

    /**
     * 491. Increasing Subsequences
     * <p>
     * https://leetcode.com/problems/increasing-subsequences
     * <p>
     * Given an integer array, your task is to find all the different possible increasing subsequences of the given
     * array, and the length of an increasing subsequence should be at least 2 .
     * 
     * Example: Input: [4, 6, 7, 7] Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     * Note: The length of the given array will not exceed 15. The range of integer in the given array is [-100,100].
     * The given array may contain duplicates, and two equal integers should also be considered as a special case of
     * increasing sequence.
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        dfsIncresing(nums, 0, new ArrayList<>(), res);
        return new ArrayList<>(res);
    }

    private void dfsIncresing(int[] nums, int start, List<Integer> list, Set<List<Integer>> res) {
        if (list.size() > 1) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < nums.length; i++) {
            if (list.size() == 0 || list.get(list.size() - 1) <= nums[i]) {
                list.add(nums[i]);
                dfsIncresing(nums, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 286. Walls and Gates
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
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfsCalDistance(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfsCalDistance(int[][] rooms, int y, int x, int distance) {
        rooms[y][x] = distance;
        int[] move = new int[] { 1, 0, -1, 0, 1 };
        for (int i = 0; i < move.length - 1; i++) {
            int nY = y + move[i];
            int nX = x + move[i + 1];
            if (canMove(rooms, nY, nX, distance)) {
                dfsCalDistance(rooms, nY, nX, distance + 1);
            }
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
        List<int[]> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pReach = new boolean[m][n];
        boolean[][] aReach = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfsReachOcean(matrix, pReach, Integer.MIN_VALUE, i, 0);
            dfsReachOcean(matrix, aReach, Integer.MIN_VALUE, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfsReachOcean(matrix, pReach, Integer.MIN_VALUE, 0, j);
            dfsReachOcean(matrix, aReach, Integer.MIN_VALUE, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pReach[i][j] && aReach[i][j]) {
                    res.add(new int[] { i, j });
                }
            }
        }
        return res;
    }

    private void dfsReachOcean(int[][] matrix, boolean[][] reach, int height, int y, int x) {
        int[] move = new int[] { 1, 0, -1, 0, 1 };
        reach[y][x] = true;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < move.length - 1; i++) {
            int newY = y + move[i];
            int newX = x + move[i + 1];
            if (newY > m - 1 || newY < 0 || newX > n - 1 || newX < 0 || reach[newY][newX]
                    || matrix[newY][newX] < matrix[y][x]) {
                continue;
            }
            dfsReachOcean(matrix, reach, matrix[y][x], newY, newX);
        }
    }

    /**
     * 529. Minesweeper
     * <p>
     * https://leetcode.com/problems/minesweeper
     * <p>
     * Let's play the minesweeper game (Wikipedia, online game)!
     *
     * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an
     * unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right,
     * and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square,
     * and finally 'X' represents a revealed mine.
     *
     * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return
     * the board after revealing this position according to the following rules:
     *
     * If a mine ('M') is revealed, then the game is over - change it to 'X'. If an empty square ('E') with no adjacent
     * mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be
     * revealed recursively. If an empty square ('E') with at least one adjacent mine is revealed, then change it to a
     * digit ('1' to '8') representing the number of adjacent mines. Return the board when no more squares will be
     * revealed.
     * </p>
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        int[] moves = new int[] { 1, 0, -1, 0, 1, 1, -1, -1, 1 };
        int y = click[0];
        int x = click[1];
        int m = board.length;
        int n = board[0].length;
        if (board[y][x] == 'M') {
            board[y][x] = 'X';
        } else {
            int count = 0;
            for (int i = 0; i < moves.length - 1; i++) {
                int newY = y + moves[i];
                int newX = x + moves[i + 1];
                if (newY < 0 || newY > m - 1 || newX < 0 || newX > n - 1) {
                    continue;
                }
                if (board[newY][newX] == 'M') {
                    count++;
                }
            }
            if (count > 0) {
                board[y][x] = (char) (count + '0');
            } else {
                board[y][x] = 'B';
                for (int i = 0; i < moves.length - 1; i++) {
                    int newY = y + moves[i];
                    int newX = x + moves[i + 1];
                    if (newY < 0 || newY > m - 1 || newX < 0 || newX > n - 1) {
                        continue;
                    }
                    if (board[newY][newX] == 'E') {
                        updateBoard(board, new int[] { newY, newX });
                    }
                }
            }
        }
        return board;
    }

    /**
     * 332. Reconstruct Itinerary
     * <p>
     * https://leetcode.com/problems/reconstruct-itinerary
     * <p>
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
     * the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin
     * with JFK.
     * 
     * Note: If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
     * order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
     * ["JFK", "LGB"]. All airports are represented by three capital letters (IATA code). You may assume all tickets
     * form at least one valid itinerary. Example 1: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR",
     * "SFO"]] Return ["JFK", "MUC", "LHR", "SFO", "SJC"]. Example 2: tickets =
     * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]] Return
     * ["JFK","ATL","JFK","SFO","ATL","SFO"]. Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
     * But it is larger in lexical order.
     * </p>
     * 
     * @param tickets
     * @return
     */
    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> res = new LinkedList<>();
        if (tickets == null || tickets.length == 0) {
            return res;
        }
        Map<String, PriorityQueue<String>> flights = new HashMap<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfsFly("JFK", res, flights);
        return res;
    }

    private void dfsFly(String departure, LinkedList<String> res, Map<String, PriorityQueue<String>> flights) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            dfsFly(arrivals.poll(), res, flights);
        }
        res.addFirst(departure);
    }

    /**
     * 721. Accounts Merge
     * <p>
     * https://leetcode.com/problems/accounts-merge
     * <p>
     * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a
     * name, and the rest of the elements are emails representing emails of the account.
     * 
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some
     * email that is common to both accounts. Note that even if two accounts have the same name, they may belong to
     * different people as people could have the same name. A person can have any number of accounts initially, but all
     * of their accounts definitely have the same name.
     * 
     * After merging the accounts, return the accounts in the following format: the first element of each account is the
     * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any
     * order.
     *
     * Note:
     * 
     * The length of accounts will be in the range [1, 1000].
     * 
     * The length of accounts[i] will be in the range [1, 10].
     * 
     * The length of accounts[i][j] will be in the range [1, 30].
     * </p>
     * 
     * todo
     * 
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] maze = { //
                { 0, 0, 1, 0, 0 }, //
                { 0, 0, 0, 0, 0 }, //
                { 0, 0, 0, 1, 0 }, //
                { 1, 1, 0, 1, 1 }, //
                { 0, 0, 0, 0, 0 } };

        int[][] maze2 = { //
                { 0, 0, 1, 0, 0 }, //
                { 0, 0, 0, 0, 0 }, //
                { 0, 0, 0, 1, 0 }, //
                { 1, 1, 0, 1, 1 }, //
                { 0, 0, 0, 0, 0 } };
        // System.out.println(solution.hasPath(maze, new int[] { 0, 4 }, new int[] { 3, 2 }));
        System.out.println(solution.hasPath(maze2, new int[] { 0, 4 }, new int[] { 4, 4 }));
        // ListNode head = new ListNode(1);
        // head.next = new ListNode(3);
        // System.out.println(new Solution().sortedListToBST(head));
        // String tes = "3[a2[b]]4[c]";
        // System.out.println(solution.decodeString(tes));
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        // System.out.println(solution.pathSum(root, 22));
    }
}
