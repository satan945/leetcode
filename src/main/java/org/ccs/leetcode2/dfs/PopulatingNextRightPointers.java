/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import org.ccs.leetcode.bean.TreeLinkNode;

/**
 * 116. Populating Next Right Pointers in Each Node
 * 
 * @author abel created on 2018/1/3 下午3:48
 * @version $Id$
 */
public class PopulatingNextRightPointers {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode node;
        while (pre.left != null) {
            node = pre;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            pre = pre.left;
        }
    }
}
