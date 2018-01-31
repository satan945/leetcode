/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest65;

/**
 * 754. Reach a Number
 * 
 * @author abel created on 2018/1/30 下午4:39
 * @version $Id$
 */
public class ReachANumber {

    public int reachNumber(int target) {
        if (target < 0) {
            target = -target;
        }
        long sq = (long) Math.floor(Math.sqrt(target * 2));
        long sum = sq * (sq + 1) / 2;
        while (sum < target || (sum - target) % 2 != 0) {
            sq++;
            sum = (sq) * (sq + 1) / 2;
        }
        return (int) sq;
    }

}
