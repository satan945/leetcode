/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * <p>
 * CSS colors can be defined in the hexadecimal notation "#rgb", a shorthand for "#rrggbb". For example, "#03f" is
 * equivalent to "#0033ff". Suppose the similarity between two colors "#abcdef" and "#ghijkl" is defined as (-(ab-gh)^2
 * - (cd-ij)^2 - (ef-kl)^2), write a function that accepts a color in the "#abcdef" format and returns a "#rgb" color
 * that is most similar to the input. For example, given "#09f166", "#1e6" should be returned.
 * </p>
 * 
 * @author abel created on 2018/3/4 下午9:58
 * @version $Id$
 */
public class CSSColor {

    public String getSimilar(String s) {
        char[] ch = s.toCharArray();
        String t = "0123456789abcdef";
        int max = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 15; j++) {
                for (int k = 0; k <= 15; k++) {
                    int distance = getDistance(t, ch, i, j, k);
                    if (distance < max) {
                        max = distance;
                        res = "#" + t.charAt(i) + t.charAt(j) + t.charAt(k);
                    }
                }
            }
        }
        return res;
    }

    private int getDistance(String t, char[] ch, int i, int j, int k) {
        int A = t.indexOf(ch[1] + "") * 16 + t.indexOf(ch[2] + "");
        int B = t.indexOf(ch[3] + "") * 16 + t.indexOf(ch[4] + "");
        int C = t.indexOf(ch[5] + "") * 16 + t.indexOf(ch[6] + "");
        int power1 = (A - 16 * i - i) * (A - 16 * i - i);
        int power2 = (B - 16 * j - j) * (B - 16 * j - j);
        int power3 = (C - 16 * k - k) * (C - 16 * k - k);
        return power1 + power2 + power3;
    }

    public static void main(String[] args) {
        System.out.println(new CSSColor().getSimilar("#09f166"));
    }
}
