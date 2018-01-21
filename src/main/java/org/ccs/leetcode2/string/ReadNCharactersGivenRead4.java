/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 157. Read N Characters Given Read4
 * <p>
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * 
 * Note: The read function will only be called once for each test case.
 * </p>
 * 
 * The read4 API is defined in the parent class Reader4. int read4(char[] buf);
 ** 
 * @author abel created on 2018/1/17 下午10:17
 * @version $Id$
 */
public class ReadNCharactersGivenRead4 extends Reader4 {

    /**
     * @param buf Destination buffer
     * @param n Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        boolean eof = false;
        int total = 0;
        while (!eof && total < n) {
            char[] tmp = new char[4];
            int cnt = read4(tmp);
            if (cnt < 4) {
                eof = true;
            }
            cnt = Math.min(n-total,cnt);
            for (int i = 0; i < cnt; i++) {
                buf[total++] = tmp[i];
            }
        }
        return buf.length;

    }

}
