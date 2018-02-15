/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.utils;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Abel created on 2018/1/5 19:03
 * @version $Id$
 */
public class TreeDeserializer {
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }

    // Generate preorder string
    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null)
            return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    // Use queue to simplify position move
    private TreeNode deserial(Queue<String> q) {
        if(q.isEmpty()){
            return null;
        }
        String val = q.poll();
        if ("#".equals(val))
            return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }

    public static void main(String[] args) {
        TreeDeserializer treeDeserializer = new TreeDeserializer();
        TreeNode node = treeDeserializer.deserialize("1,2,3,4");
        System.out.println(node);
    }
}
