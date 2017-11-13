/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.hashtable.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author abel created on 2017/11/7 下午9:23
 * @version $Id$
 */
public class RandomizedSet {
    private ArrayList<Integer> numList;
    private HashMap<Integer, Integer> locMap;
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        numList = new ArrayList<>();
        locMap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (locMap.containsKey(val)) {
            return false;
        }
        locMap.put(val, numList.size());
        numList.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!locMap.containsKey(val)) {
            return false;
        }
        int loc = locMap.get(val);
        if (loc < numList.size() - 1) {
            int lastVal = numList.get(numList.size() - 1);
            numList.set(loc, lastVal);
            locMap.put(lastVal, loc);
        }
        locMap.remove(val);
        numList.remove(numList.size() - 1);
        return true;

    }

    /** Get a random element from the set. */
    public int getRandom() {
        return numList.get(random.nextInt(numList.size()));
    }
}
