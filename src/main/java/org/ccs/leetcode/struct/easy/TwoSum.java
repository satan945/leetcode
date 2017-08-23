/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.struct.easy;

/**
 * @author abel created on 2017/8/22 下午5:47
 * @version $Id$
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 170. Two Sum III - Data structure design
 * <p>
 * https://leetcode.com/problems/two-sum-iii-data-structure-design
 * <p>
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there exists any pair of numbers which sum is
 * equal to the value.
 * 
 * For example, add(1); add(3); add(5); find(4) -> true find(7) -> false
 * 
 * </p>
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there exists any pair of numbers which sum is
 * equal to the value.
 */
public class TwoSum {

    private List<Integer> list = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    /** Initialize your data structure here. */
    public TwoSum() {
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
        }
        list.add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (list.size() <= 1) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            int rest = value - list.get(i);
            if ((rest == list.get(i) && map.get(rest) > 1) || (rest != list.get(i) && map.get(rest) != null)) {
                return true;
            }
        }
        return false;
    }
}
