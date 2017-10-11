/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.stack.medium;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 341. Flatten Nested List Iterator
 * <p>
 * https://leetcode.com/problems/flatten-nested-list-iterator
 * <p>
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,1,2,1,1].
 * 
 * Example 2: Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * 
 * </p>
 * 
 * @author abel created on 2017/10/9 下午11:47
 * @version $Id$
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }
}
