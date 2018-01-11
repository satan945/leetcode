/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.binarysearch;

/**
 * 278. First Bad Version
 * 
 * @author abel created on 2018/1/10 下午9:58
 * @version $Id$
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int l = 0, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;

    }

    public boolean isBadVersion(int version) {
        if (version % 100 < 96) {
            return false;
        }
        return true;
    }
}
