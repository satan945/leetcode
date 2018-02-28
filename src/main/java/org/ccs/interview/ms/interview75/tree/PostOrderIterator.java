/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;
import org.ccs.utils.TreeDeserializer;

import java.util.Stack;

/**
 * 26 . 3叔，问了binary tree 的 postorder iterator，implement next和has next，之前没做过iterator version的，有点懵，最
 * 后磕磕碰碰用stack做root到cur的路径做出来了，感觉不太好。 全部都是两种方法
 * todo
 * @author abel created on 2018/2/23 下午5:20
 * @version $Id$
 */
public class PostOrderIterator {

    private Stack<TreeNode> stack;

    public PostOrderIterator(TreeNode root) {
        stack = new Stack<>();
        if (root == null) {
            return;
        }
        addNode(root);

    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public TreeNode next() {
        if (stack.isEmpty()) {
            return null;
        }
        TreeNode node = stack.pop();
        addNode(node.right);
        return node;
    }

    private void addNode(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {

        TreeDeserializer treeDeserializer = new TreeDeserializer();
        TreeNode root = treeDeserializer.deserialize("1,2,X,4,X,X,3,5,6,X,X,7,X,X,X,");
        PostOrderIterator iterator = new PostOrderIterator(root);
        while(iterator.hasNext()){
            TreeNode node = iterator.next();
            System.out.println(node.val);
        }
    }


}
