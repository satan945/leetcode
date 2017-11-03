/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bfs.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
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
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return new int[1];
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

    public static void main(String[] args) {
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
        System.out.println(new Solution().ladderLength("hit", "cog", stringList));
    }
}
