/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.unsorted.medium;

/**
 * 390. Elimination Game
 * <p>
 * https://leetcode.com/problems/elimination-game
 * <p>
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other
 * number afterward until you reach the end of the list.
 * 
 * Repeat the previous step again, but this time from right to left, remove the right most number and every other number
 * from the remaining numbers.
 * 
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 * 
 * Find the last number that remains starting with a list of length n.
 * 
 * Example:
 * 
 * Input: n = 9,
 * 
 * 1 2 3 4 5 6 7 8 9
 * 
 * 2 4 6 8
 * 
 * 2 6
 * 
 * 6
 * 
 * Output: 6
 * </p>
 * https://discuss.leetcode.com/topic/59293/java-easiest-solution-o-logn-with-explanation/3
 * 
 * @author abel created on 2017/10/26 下午10:01
 * @version $Id$
 */
public class EliminationGame {

    public int lastRemaining(int n) {
        boolean left = true;
        int step = 1;
        int head = 1;
        int remaining = n;

        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
    }

    public int lastRemainingRecur(int n) {
        return leftToRight(n);
    }

    private static int leftToRight(int n) {
        if (n <= 2) {
            return n;
        }
        return 2 * rightToLeft(n / 2);
    }

    private static int rightToLeft(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n % 2 == 1) {
            return 2 * leftToRight(n / 2);
        }
        return 2 * leftToRight(n / 2) - 1;
    }
}
