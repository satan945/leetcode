/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.interview.ms;

/**
 * @author Abel created on 2018/3/11 18:09
 * @version $Id$
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }
        int[] s1Map = new int[26];
        int[] s2Map = new int[26];
        for(int i = 0;i < s1.length();i++){
            s1Map[s1.charAt(i)-'a']++;
            s2Map[s2.charAt(i)-'a']++;
        }
        for(int i = 0;i < s2.length() - s1.length();i++){
            if(match(s1Map,s2Map)){
                return true;
            }
            s2Map[s2.charAt(i+s1.length())-'a']++;
            s2Map[s2.charAt(i)-'a']--;
        }
        return false;
    }
    private boolean match(int[] map1,int[] map2){
        for(int i=0;i<map1.length;i++){
            if(map1[i]!=map2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "adc";
        String s2 = "dacd";
        System.out.println(new PermutationInString().checkInclusion(s1,s2));
    }
}
