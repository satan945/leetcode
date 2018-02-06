/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.utils;

/**
 * @author abel created on 2018/2/1 下午3:56
 * @version $Id$
 */
public class CommonUtils {

    public static void swapIntegerArray(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
