/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 284. Peeking Iterator
 * 
 * https://leetcode.com/problems/peeking-iterator
 * 
 * @author abel created on 2018/3/9 下午10:31
 * @version $Id$
 */
public class PeekingIterator2 implements Iterator<Integer> {

    private Integer next = null;
    private Iterator<Integer> iter;

    public PeekingIterator2(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext())
            next = iter.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
