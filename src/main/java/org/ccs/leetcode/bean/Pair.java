/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bean;

/**
 * @author abel created on 2017/10/19 下午8:36
 * @version $Id$
 */
public class Pair<T, F> {
    public T leftValue;
    public F rightValue;

    public Pair(T leftValue, F rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public String toString() {
        return "" + leftValue + "," + rightValue;
    }
}
