/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.design.easy;

import java.util.ArrayList;

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
    private ArrayList<Integer> stack = new ArrayList<>();
    private ArrayList<Integer> minStack = new ArrayList<>();
    private int min;

    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        stack.add(x);
        if (minStack.isEmpty() || x <= minStack.get(minStack.size() - 1)) {
            minStack.add(x);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int ele = stack.remove(stack.size() - 1);
        if (!minStack.isEmpty() && ele == minStack.get(minStack.size() - 1)) {
            minStack.remove(minStack.size() - 1);
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return 0;
        }
        return stack.get(stack.size() - 1);
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.get(minStack.size() - 1);
        }
        return 0;
    }
}
