/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.ListNode;
import org.ccs.leetcode.bean.TreeNode;

/**
 * @author abel created on 2018/1/3 下午4:32
 * @version $Id$
 */
public class ConvertSortedListBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }
        ListNode slow = start;
        ListNode fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildBST(start, slow);
        root.right = buildBST(slow.next, end);
        return root;
    }

}
