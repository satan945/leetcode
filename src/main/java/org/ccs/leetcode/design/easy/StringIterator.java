/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.easy;

/**
 * @author abel created on 2017/8/22 下午6:39
 * @version $Id$
 */

/**
 * 604. Design Compressed String Iterator
 * <p>
 * https://leetcode.com/problems/design-compressed-string-iterator
 * <p>
 * 
 * Design and implement a data structure for a compressed string iterator. It should support the following operations:
 * next and hasNext.
 * 
 * The given compressed string will be in the form of each letter followed by a positive integer representing the number
 * of this letter existing in the original uncompressed string.
 * 
 * next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white
 * space. hasNext() - Judge whether there is any letter needs to be uncompressed.
 * 
 * Note: Please remember to RESET your class variables declared in StringIterator, as static/class variables are
 * persisted across multiple test cases. Please see here for more details.
 * 
 * Example:
 * 
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * 
 * iterator.next(); // return 'L'
 * 
 * iterator.next(); // return 'e'
 * 
 * iterator.next(); // return 'e'
 * 
 * iterator.next(); // return 't'
 * 
 * iterator.next(); // return 'C'
 * 
 * iterator.next(); // return 'o'
 * 
 * iterator.next(); // return 'd'
 * 
 * iterator.hasNext(); // return true
 * 
 * iterator.next(); // return 'e'
 * 
 * iterator.hasNext(); // return false
 * 
 * iterator.next(); // return ' '
 * </p>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Your StringIterator object will be instantiated and called as such:
 * 
 * StringIterator obj = new StringIterator(compressedString);
 * 
 * char param_1 = obj.next();
 * 
 * boolean param_2 = obj.hasNext();
 */
public class StringIterator {

    int addr = 0;
    List<Character> charSeq;
    List<Integer> count;

    public StringIterator(String compressedString) {
        char[] total = compressedString.toCharArray();
        count = new ArrayList<>();
        charSeq = new ArrayList<>();
        charSeq.add(total[0]);
        int temp = 0;
        for (int i = 1; i < total.length; i++) {
            if (total[i] - '0' >= 0 && total[i] - '0' <= 9) {
                temp = temp * 10 + total[i] - '0';
                if (i == total.length - 1) {
                    count.add(temp);
                }
            } else {
                charSeq.add(total[i]);
                count.add(temp);
                temp = 0;
            }
        }
    }

    public char next() {
        int curCount = count.get(addr);
        if (addr == charSeq.size() - 1 && curCount == 0) {
            return ' ';
        }
        if (curCount == 0) {
            addr++;
            curCount = count.get(addr);
        }
        curCount--;
        count.set(addr, curCount);
        return charSeq.get(addr);
    }

    public boolean hasNext() {
        if (addr == charSeq.size() - 1 && count.get(addr) == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
