/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

/**
 * 531. Lonely Pixel I
 * <p>
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 * 
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels
 * respectively.
 * 
 * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't
 * have any other black pixels.
 * </p>
 * 
 * @author Abel created on 2018/1/29 21:08
 * @version $Id$
 */
public class LonelyPixelI {

    public int findLonelyPixel(char[][] picture) {
        int[] row = new int[picture.length];
        int[] col = new int[picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B') {
                    if (row[i] == 1 && col[j] == 1) {
                        res++;
                    }
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        char[][] pic = { { 'W', 'W', 'B' }, { 'W', 'B', 'W' }, { 'B', 'W', 'W' } };
        System.out.println(new LonelyPixelI().findLonelyPixel(pic));
    }
}
