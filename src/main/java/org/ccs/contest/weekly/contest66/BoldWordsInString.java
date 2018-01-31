/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest66;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 758. Bold Words in String
 * 
 * @author abel created on 2018/1/30 下午4:43
 * @version $Id$
 */
public class BoldWordsInString {

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

    public static void main(String[] args) {
        BoldWordsInString solution = new BoldWordsInString();
        String[] words5 = new String[] { "di", "r", "buhozb", "lofjmyjj", "qagllw", "zzuid", "loyugfh", "w", "hcfg",
                "ttd", "vjqigvx", "u", "mhbivve", "x", "nzbvyfzx", "zs", "j", "zgtud", "zm", "huevyex", "szwigrlwzm",
                "vlrjmobu", "b", "h", "gcmdgyv", "anyfelm", "vtcejv", "myjjzn", "jznnj", "awcxmjn", "lw", "sju",
                "szszwigrl", "eze", "ffikvecua", "bklrhsju", "gyazwel", "pdhnsxsod", "zn", "rhsjus", "zk", "gctgu",
                "vzndt", "mfd", "jlws", "j", "zxgaudyo", "apa", "znvixpdh", "tgubzczgt" };
        System.out.println(solution.boldWords(words5,
                "wwcyuaqzgtudmpjkluqoseslygywzkixjqghsocvjqigvxwqloyugfhcjscjghqmiglgyazwelshzapaezqgmcmrmfrfzttdgquizyducbvxzzuiddcnwuaapdunzlbagnifndbjyalqqgbramhbivvervxrtcszszwigrlwzmuteyswzagudtpvlrjmobuhozbghkhvoxawcxmjnazlqlkqqqnoclufgkovbokvkoezeknwhcfgcenvaablpvtcejvzndtzncrelhedwlwiqgdbdgctgubzczgtovufncicjlwsmfdcrqeaghuevyexqdhffikvecuazrelofjmyjjznnjdkimbklrhsjusbstqhvlejtjcczqnzbvyfzxgaudyosckysmminoanjmbafhtnbrrzqagllwxlxmjanyfelmwruftlzuuhbsjexoobjkmymlitiwjtdxscotzvznvixpdhnsxsodieatipiaodgcmdgyv"));
    }
}
