/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeLinkNode;

/**
 * 116. Populating Next Right Pointers in Each Node
 * 
 * @author Abel created on 2018/1/26 0:38
 * @version $Id$
 */
public class PopulatingNextRightPointersInEachNode {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode node = pre;
        while (pre != null) {
            while (node != null) {
                if (node.left != null && node.right != null) {
                    node.left.next = node.right;
                }
                if (node.right != null && node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            node = pre.left;
            pre = node;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode l = new TreeLinkNode(2);
        TreeLinkNode r = new TreeLinkNode(3);
        root.left=l;
        root.right=r;
        PopulatingNextRightPointers populatingNextRightPointers =new PopulatingNextRightPointers();
        populatingNextRightPointers.connect(root);
        System.out.println("111");
    }
}
