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
public class PeekingIterator implements Iterator<Integer> {

    private Queue<Integer> queue;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        queue = new LinkedList<>();
        while (iterator.hasNext()) {
            queue.add(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return queue.peek();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Integer next() {
        return queue.poll();
    }
}
