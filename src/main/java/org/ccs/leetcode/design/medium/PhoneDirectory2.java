/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.PriorityQueue;

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
public class PhoneDirectory2 {
    private PriorityQueue<Integer> queue;
    private boolean[] used;

    /**
     * Initialize your data structure here
     * 
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory2(int maxNumbers) {
        queue = new PriorityQueue<>();
        used = new boolean[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     * 
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (queue.isEmpty()) {
            return -1;
        }
        int num = queue.poll();
        used[num] = true;
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used[number];

    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (used[number]) {
            used[number] = false;
            queue.offer(number);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory2 phoneDirectory2 = new PhoneDirectory2(3);
        System.out.println(phoneDirectory2.check(2));
        System.out.println(phoneDirectory2.get());
        System.out.println(phoneDirectory2.get());
        phoneDirectory2.release(2);
        System.out.println(phoneDirectory2.check(1));
        System.out.println(phoneDirectory2.get());
        System.out.println(phoneDirectory2.get());

    }

}
