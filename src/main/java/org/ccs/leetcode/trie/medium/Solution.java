/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.trie.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abel created on 2017/11/20 17:00
 * @version $Id$
 */
public class Solution {

    /**
     * 648. Replace Words
     * <p>
     * https://leetcode.com/problems/replace-words
     * <p>
     * In English, we have a concept called root, which can be followed by some other words to form another longer word
     * - let's call this word successor. For example, the root an, followed by other, which can form another word
     * another.
     * 
     * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the
     * sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the
     * shortest length.
     * 
     * You need to output the sentence after the replacement.
     * 
     * Example 1: Input: dict = ["cat", "bat", "rat"] sentence = "the cattle was rattled by the battery"
     *
     * Output: "the cat was rat by the bat"
     *
     * Note:
     *
     * The input will only have lower-case letters.
     *
     * 1 <= dict words number <= 1000
     * 
     * 1 <= sentence words number <= 1000
     * 
     * 1 <= root length <= 100
     * 
     * 1 <= sentence words length <= 1000
     * 
     * </p>
     * 
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = buildTrie(dict);
        return replaceShort(root, sentence);
    }

    private String replaceShort(TrieNode root, String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(getShortestReplacement(word, root));
            sb.append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String getShortestReplacement(String word, TrieNode root) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            sb.append(ch);
            if (node.getNodes()[ch - 'a'] != null) {
                if (node.getNodes()[ch - 'a'].isEnd()) {
                    return sb.toString();
                }
                node = node.getNodes()[ch - 'a'];
            } else {
                return word;
            }
        }
        return word;
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.getNodes()[ch - 'a'] == null) {
                    node.getNodes()[ch - 'a'] = new TrieNode();
                }
                node = node.getNodes()[ch - 'a'];
            }
            node.setEnd(true);
        }
        return root;
    }

    class TrieNode {
        private static final int R = 26;
        private TrieNode[] nodes;
        private boolean isEnd;

        public TrieNode() {
            this.nodes = new TrieNode[R];
        }

        public TrieNode[] getNodes() {
            return nodes;
        }

        public void setNodes(TrieNode[] nodes) {
            this.nodes = nodes;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }

    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");
        Solution solution = new Solution();
        System.out.println(solution.replaceWords(dict, "the cattle was rattled by the battery"));
    }
}
