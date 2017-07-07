/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.struct.easy;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * <p>
 * https://leetcode.com/problems/implement-queue-using-stacksval
 * 
 * <p>
 * Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue. pop() -- Removes the element from in front of queue. peek() -- Get
 * the front element. empty() -- Return whether the queue is empty. Notes: You must use only standard operations of a
 * stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid. Depending on your
 * language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended
 * queue), as long as you use only standard operations of a stack. You may assume that all operations are valid (for
 * example, no pop or peek operations will be called on an empty queue).
 * 
 * </p>
 * 
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
