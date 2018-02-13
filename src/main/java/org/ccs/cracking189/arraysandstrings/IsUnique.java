/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.cracking189.arraysandstrings;

/**
 * Determine if a string has all unique characters
 * 
 * @author Abel created on 2018/2/7 21:26
 * @version $Id$
 */
public class IsUnique {

    public boolean isUnique(String str) {
        boolean[] occured = new boolean[128];
        if (str.length() > 128) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (occured[val]) {
                return false;
            }
            occured[val] = true;
        }
        return true;
    }

}
