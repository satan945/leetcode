/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeLinkNode;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * 
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 * <p>
 * 变形，每层最后一个 next 指向下层最左node
 * 
 * </p>
 * 
 * @author abel created on 2018/2/22 下午5:04
 * @version $Id$
 */
public class PopulatingNextRightPointersInEachNodeII {

    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode tempChild = new TreeLinkNode(0);
            TreeLinkNode currentChild = tempChild;
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode l = new TreeLinkNode(2);
        TreeLinkNode r = new TreeLinkNode(3);
        TreeLinkNode lr = new TreeLinkNode(4);
        TreeLinkNode rr = new TreeLinkNode(5);
        TreeLinkNode rrr = new TreeLinkNode(6);
        root.left = l;
        root.right = r;
        l.right = lr;
        r.right = rr;
        rr.right = rrr;
        PopulatingNextRightPointersInEachNodeII solution = new PopulatingNextRightPointersInEachNodeII();
        solution.connect(root);
        System.out.println(root);
    }
}
