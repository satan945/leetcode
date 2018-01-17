/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.design;

import java.util.Stack;

/**
 * 716. Max Stack
 * 
 * @author abel created on 2018/1/16 下午9:35
 * @version $Id$
 */
public class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        int max = maxStack.isEmpty() ? x : Math.max(x, maxStack.peek());
        maxStack.push(max);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> cache = new Stack<>();
        while (stack.peek() != max) {
            cache.push(pop());
        }
        stack.pop();
        maxStack.pop();
        while (!cache.isEmpty()) {
            push(cache.pop());
        }
        return max;
    }
}
