/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest67;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 762. Prime Number of Set Bits in Binary Representation
 * <p>
 * No.1
 * 
 * Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set
 * bits in their binary representation.
 * 
 * (Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example,
 * 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
 * </p>
 * 
 * @author abel created on 2018/1/16 下午5:52
 * @version $Id$
 */
public class PrimeNumberSetBitsBinaryRepresentation {

    public int countPrimeSetBits(int L, int R) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        int cnt = 0;
        for (int i = L; i <= R; i++) {
            int bits = 0;
            for (int n = i; n > 0; n >>= 1) {
                bits += n & 1;
            }
            cnt += primes.contains(bits) ? 1 : 0;
        }
        return cnt;
    }

    public int countPrimeSetBits2(int L, int R) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        int cnt = 0;
        for (int i = L; i <= R; i++) {
            String bits = Integer.toBinaryString(i);
            int sets = 0;
            for (int j = 0; j < bits.length(); j++) {
                sets += bits.charAt(j) == '1' ? 1 : 0;
            }
            cnt += primes.contains(sets) ? 1 : 0;
        }
        return cnt;
    }

}
