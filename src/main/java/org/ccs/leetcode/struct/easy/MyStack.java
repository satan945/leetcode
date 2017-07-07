/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.struct.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * <p>
 * https://leetcode.com/problems/implement-stack-using-queuesval
 * <p>
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack. pop() -- Removes the element on top of the stack. top() -- Get the top element.
 * empty() -- Return whether the stack is empty. Notes: You must use only standard operations of a queue -- which means
 * only push to back, peek/pop from front, size, and is empty operations are valid. Depending on your language, queue
 * may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you
 * use only standard operations of a queue. You may assume that all operations are valid (for example, no pop or top
 * operations will be called on an empty stack). Credits: Special thanks to @jianchao.li.fighter for adding this problem
 * and all test cases.
 * </p>
 * 
 * @author Abel created on 2017/7/7 12:12
 * @version $Id$
 */
class MyStack {
    Queue<Integer> q = new LinkedList<Integer>();

    // Push element x onto stack.
    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.remove());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        q.remove();
    }

    // Get the top element.
    public int top() {
        return q.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}
