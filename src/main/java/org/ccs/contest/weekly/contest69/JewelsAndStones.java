/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest69;

import java.util.HashSet;
import java.util.Set;

/**
 * 771. Jewels and Stones
 * <p>
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have. Each
 * character in S is a type of stone you have. You want to know how many of the stones you have are also jewels.
 * 
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so
 * "a" is considered a different type of stone from "A".
 * </p>
 * 
 * @author Abel created on 2018/1/30 18:18
 * @version $Id$
 */
public class JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        Set<Character> jSet = new HashSet<>();
        for (int i = 0; i < J.length(); i++) {
            jSet.add(J.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (jSet.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
