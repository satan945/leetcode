/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bean;

/**
 * @author abel created on 2017/8/16 下午4:58
 * @version $Id$
 */
public class TreeLinkNode {
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;
    public int val;

    public TreeLinkNode(int val) {
        this.val = val;
    }
}
