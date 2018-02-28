/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * 299. Bulls and Cows
 * 
 * @author abel created on 2018/2/27 下午10:45
 * @version $Id$
 */
public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int[] countS = new int[10];
        int[] countG = new int[10];
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < secret.length(); i++) {
            char chS = secret.charAt(i);
            char chG = guess.charAt(i);
            if (chS == chG) {
                countA++;
            } else {
                countS[chS - '0']++;
                countG[chG - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            countB += Math.min(countG[i], countS[i]);
        }
        return countA + "A" + countB + "B";
    }

    public static void main(String[] args) {
        BullsAndCows bullsAndCows = new BullsAndCows();
        System.out.println(bullsAndCows.getHint("1807","7810"));
    }
}
