/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeLinkNode;

/**
 * 116. Populating Next Right Pointers in Each Node
 * 
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node
 * 
 * @author abel created on 2018/2/27 下午11:16
 * @version $Id$
 */
public class PopulatingNextRightPointersInEachNode {

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode cur;
        while (pre.left != null) {
            cur = pre;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;

            }
            pre = pre.left;
        }

    }
}
