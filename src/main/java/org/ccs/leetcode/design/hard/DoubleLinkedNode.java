/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.hard;

/**
 * @author abel created on 2017/8/22 下午6:22
 * @version $Id$
 */
public class DoubleLinkedNode {

    public int key;
    public int value;
    public DoubleLinkedNode pre;
    public DoubleLinkedNode next;

    public DoubleLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
