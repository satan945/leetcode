/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.struct.easy;

import java.util.Stack;

/**
 * 155. Min Stack
 * <p>
 * https://leetcode.com/problems/min-stack
 *
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * 
 * pop() -- Removes the element on top of the stack.
 *
 * top() -- Get the top element.
 * 
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * Example: MinStack minStack = new MinStack();
 * 
 * minStack.push(-2);
 * 
 * minStack.push(0);
 * 
 * minStack.push(-3);
 * 
 * minStack.getMin(); --> Returns -3.
 * 
 * minStack.pop();
 * 
 * minStack.top(); --> Returns 0.
 * 
 * minStack.getMin(); --> Returns -2.
 * </p>
 * 
 * @author Abel created on 2017/7/26 16:54
 * @version $Id$
 */
public class MinStack {
    private Stack<Integer> minStack;
    private int min;

    /** initialize your data structure here. */
    public MinStack() {
        minStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        minStack.push(x);
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        minStack.pop();
    }

    public int top() {
        return minStack.peek();
    }

    public int getMin() {
        if (!minStack.empty()) {
            return min;
        } else {
            return 0;
        }
    }
}
