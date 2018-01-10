/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest66;

import org.ccs.leetcode.bean.Interval;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * 
 * @author abel created on 2018/1/6 下午6:31
 * @version $Id$
 */
public class Solution {

    /**
     * 760. Find Anagram Mappings
     * <p>
     * Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order
     * of the elements in A.
     *
     * We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at
     * index j.
     *
     * These lists A and B may contain duplicates. If there are multiple answers, output any of them.
     * </p>
     * 
     * @param A
     * @param B
     * @return
     */
    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return new int[0];
        }
        int[] res = new int[A.length];
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            indexMap.putIfAbsent(B[i], new LinkedList<>());
            indexMap.get(B[i]).add(i);
        }
        for (int i = 0; i < A.length; i++) {
            List<Integer> list = indexMap.get(A[i]);
            res[i] = list.get(0);
            if (list.size() > 1) {
                list.remove(0);
            }
        }
        return res;
    }

    /**
     * 758. Bold Words in String
     * <p>
     * Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between
     * <b> and </b> tags become bold.
     * 
     * The returned string should use the least number of tags possible, and of course the tags should form a valid
     * combination.
     * 
     * For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that
     * returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
     * </p>
     * 
     * @param words
     * @param S
     * @return
     */
    public String boldWords(String[] words, String S) {
        if (words == null || words.length == 0) {
            return S;
        }
        List<int[]> boldIndex = new ArrayList<>();
        for (String word : words) {
            int begin = S.indexOf(word);
            while (begin >= 0) {
                boldIndex.add(new int[] { begin, begin + word.length() });
                begin = S.indexOf(word, begin + 1);
            }
        }
        if (boldIndex.size() == 0) {
            return S;
        }
        List<int[]> merge = new ArrayList<>();
        boldIndex.sort(Comparator.comparingInt(o -> o[0]));
        merge.add(boldIndex.get(0));
        for (int i = 1; i < boldIndex.size(); i++) {
            int[] cur = boldIndex.get(i);
            int[] pre = merge.get(merge.size() - 1);
            if (cur[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], cur[1]);
                pre[0] = Math.min(pre[0], cur[0]);
            } else {
                merge.add(cur);
            }
        }
        merge.sort(Comparator.comparingInt(o -> o[0]));
        String OPEN = "<b>";
        String CLOSE = "</b>";
        StringBuilder sb = new StringBuilder();
        sb.append(S.substring(0, merge.get(0)[0])).append(OPEN).append(S.substring(merge.get(0)[0], merge.get(0)[1]))
                .append(CLOSE);
        for (int i = 1; i < merge.size(); i++) {
            int[] pos = merge.get(i);
            int[] pre = merge.get(i - 1);
            sb.append(S.substring(pre[1], pos[0])).append(OPEN).append(S.substring(pos[0], pos[1])).append(CLOSE);
        }
        sb.append(S.substring(merge.get(merge.size() - 1)[1]));
        return sb.toString().replaceAll(CLOSE + OPEN, "");
    }

    /**
     * 759. Employee Free Time
     * <p>
     * We are given a list avail of employees, which represents the free time for each employee.
     * 
     * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
     * 
     * Return the list of finite intervals representing common, positive-length free time for all employees, also in
     * sorted order.
     * </p>
     * 
     * @param avails
     * @return
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>(
                (o1, o2) -> (o1.start == o2.start) ? o2.end - o1.end : o1.start - o2.start);
        avails.forEach(priorityQueue::addAll);
        Interval tmp = priorityQueue.poll();
        while (!priorityQueue.isEmpty()) {
            if (tmp.end < priorityQueue.peek().start) {
                res.add(new Interval(tmp.end, priorityQueue.peek().start));
                tmp = priorityQueue.poll();
            } else {
                tmp = tmp.end < priorityQueue.peek().end ? priorityQueue.peek() : tmp;
                priorityQueue.poll();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = new int[] { 12, 12, 46, 32, 50 };
        int[] B = new int[] { 50, 12, 32, 46, 12 };
        // System.out.println(solution.anagramMappings(A, B));

        String[] words5 = new String[] { "di", "r", "buhozb", "lofjmyjj", "qagllw", "zzuid", "loyugfh", "w", "hcfg",
                "ttd", "vjqigvx", "u", "mhbivve", "x", "nzbvyfzx", "zs", "j", "zgtud", "zm", "huevyex", "szwigrlwzm",
                "vlrjmobu", "b", "h", "gcmdgyv", "anyfelm", "vtcejv", "myjjzn", "jznnj", "awcxmjn", "lw", "sju",
                "szszwigrl", "eze", "ffikvecua", "bklrhsju", "gyazwel", "pdhnsxsod", "zn", "rhsjus", "zk", "gctgu",
                "vzndt", "mfd", "jlws", "j", "zxgaudyo", "apa", "znvixpdh", "tgubzczgt" };
        System.out.println(solution.boldWords(words5,
                "wwcyuaqzgtudmpjkluqoseslygywzkixjqghsocvjqigvxwqloyugfhcjscjghqmiglgyazwelshzapaezqgmcmrmfrfzttdgquizyducbvxzzuiddcnwuaapdunzlbagnifndbjyalqqgbramhbivvervxrtcszszwigrlwzmuteyswzagudtpvlrjmobuhozbghkhvoxawcxmjnazlqlkqqqnoclufgkovbokvkoezeknwhcfgcenvaablpvtcejvzndtzncrelhedwlwiqgdbdgctgubzczgtovufncicjlwsmfdcrqeaghuevyexqdhffikvecuazrelofjmyjjznnjdkimbklrhsjusbstqhvlejtjcczqnzbvyfzxgaudyosckysmminoanjmbafhtnbrrzqagllwxlxmjanyfelmwruftlzuuhbsjexoobjkmymlitiwjtdxscotzvznvixpdhnsxsodieatipiaodgcmdgyv"));
    }
}
