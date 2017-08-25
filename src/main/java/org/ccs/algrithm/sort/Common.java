/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algrithm.sort;

/**
 * @author abel created on 2017/8/25 下午3:55
 * @version $Id$
 */
public class Common {

    public static void swapArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
