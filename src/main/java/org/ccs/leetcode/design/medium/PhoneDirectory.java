/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.BitSet;

/**
 * 379. Design Phone Directory
 * <p>
 * https://leetcode.com/problems/design-phone-directory
 * <p>
 * Design a Phone Directory which supports the following operations:
 * 
 * get: Provide a number which is not assigned to anyone. check: Check if a number is available or not. release: Recycle
 * or release a number.
 * </p>
 * 
 * @author abel created on 2017/11/29 下午5:44
 * @version $Id$
 */
public class PhoneDirectory {
    private BitSet bitSet;
    private int minFree;
    int max;

    /**
     * Initialize your data structure here
     * 
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        bitSet = new BitSet(maxNumbers);
        max = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     * 
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (minFree == max) {
            return -1;
        }
        int num = minFree;
        bitSet.set(minFree);
        minFree = bitSet.nextClearBit(minFree);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !bitSet.get(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (!bitSet.get(number)) {
            return;
        }
        bitSet.clear(number);
        if (number < minFree) {
            minFree = number;
        }
    }

}
