/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.search;

/**
 * @author abel created on 2017/8/25 下午3:15
 * @version $Id$
 */
public class BinarySearch {

    /*
     * no dup
     */
    public int search1(int[] array, int key) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                l = mid + 1;
            }
            if (array[mid] > key) {
                r = mid - 1;
            }
        }
        return l;
    }

    /*
     * no dup
     */
    public int searchRecur(int[] array, int key, int low, int high) {
        if (low > high) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (array[mid] < key) {
            return searchRecur(array, key, low + 1, high);
        } else if (array[mid] > key) {
            return searchRecur(array, key, low, high - 1);
        } else {
            return mid;
        }
    }

    /*
     * first occurrence(with dup)
     */
    public int searchFirst(int[] array, int key) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] >= key) {
                r = mid - 1;
            }
            if (array[mid] < key) {
                l = mid + 1;
            }
        }
        if (array[l] == key) {
            return l;
        }
        return -1;
    }

    /*
     * last occurrence(with dup)
     */
    public int searchLast(int[] array, int key) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] <= key) {
                l = mid + 1;
            }
            if (array[mid] > key) {
                r = mid - 1;
            }
        }
        if (array[r] == key) {
            return r;
        }
        return -1;
    }
}
