/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.struct.easy;

import java.util.Stack;

/**
 * @author Abel created on 2017/7/7 11:53
 * @version $Id$
 */
public class MyQueue {
    private Stack<Integer> front;
    private Stack<Integer> rear;

    /** Initialize your data structure here. */
    public MyQueue() {
        front = new Stack<Integer>();
        rear = new Stack<Integer>();
    }

    /** Push element x to the back of struct. */
    public void push(int x) {
        front.push(x);
    }

    /** Removes the element from in front of struct and returns that element. */
    public int pop() {
        if (empty()) {
            return 0;
        }
        if (rear.isEmpty()) {
            while (!front.isEmpty()) {
                rear.push(front.pop());
            }
        }
        return rear.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (empty()) {
            return 0;
        }
        if (rear.isEmpty()) {
            while (!front.isEmpty()) {
                rear.push(front.pop());
            }
        }
        return rear.peek();
    }

    /** Returns whether the struct is empty. */
    public boolean empty() {
        return front.isEmpty() && rear.isEmpty();
    }
}
