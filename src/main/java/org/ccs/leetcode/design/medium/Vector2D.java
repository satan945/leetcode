/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author abel created on 2017/11/29 下午5:09
 * @version $Id$
 */
public class Vector2D implements Iterator<Integer> {
    private Iterator<Integer> iterator;

    public Vector2D(List<List<Integer>> vec2d) {
        List<Integer> list = new ArrayList<>();
        for (List<Integer> row : vec2d) {
            list.addAll(row);
        }
        iterator = list.iterator();

    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }
}
