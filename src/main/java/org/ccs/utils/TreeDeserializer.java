/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.utils;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Abel created on 2018/1/5 19:03
 * @version $Id$
 */
public class TreeDeserializer {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helperS(root, sb);
        return sb.toString();
    }

    private void helperS(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append("null").append(",");
            return;
        }

        sb.append(node.val).append(",");

        helperS(node.left, sb);
        helperS(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals  = data.split("[,]");
        int[]    index = new int[]{0};
        return helperD(vals, index);
    }

    private TreeNode helperD(String[] vals, int[] index){
        if(index[0] == vals.length){
            return null;
        }

        String visiting = vals[index[0]++];
        if(visiting.equals("null")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(visiting));
        node.left     = helperD(vals, index);
        node.right    = helperD(vals, index);

        return node;
    }
}
