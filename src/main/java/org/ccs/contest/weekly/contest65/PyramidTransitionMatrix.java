/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest65;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 756. Pyramid Transition Matrix
 * 
 * @author abel created on 2018/1/30 下午4:40
 * @version $Id$
 */
public class PyramidTransitionMatrix {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> indexMap = new HashMap<>();
        for (String str : allowed) {
            String key = str.substring(0, 2);
            String val = str.substring(2);
            indexMap.putIfAbsent(key, new ArrayList<>());
            indexMap.get(key).add(val);
        }
        return helper(bottom, "", 0, indexMap);
    }

    private boolean helper(String level, String nextLevel, int index, Map<String, List<String>> indexMap) {
        if (level.length() == 1) {
            return true;
        }
        int nextLen = nextLevel.length();
        if (nextLen > 1) {
            String key = nextLevel.substring(nextLen - 2);
            if (!indexMap.containsKey(key)) {
                return false;
            }
        }
        if (index == level.length() - 1) {
            return helper(nextLevel, "", 0, indexMap);
        }
        String a = level.substring(index, index + 2);
        if (!indexMap.containsKey(a)) {
            return false;
        }
        for (String str : indexMap.get(a)) {
            if (helper(level, nextLevel + str, index + 1, indexMap)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PyramidTransitionMatrix solution = new PyramidTransitionMatrix();
        int[] heights = new int[] { 1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1 };
        String bottom = "XYZ";
        List<String> allowed = new ArrayList<>();
        allowed.add("XYD");
        allowed.add("YZE");
        allowed.add("DEA");
        allowed.add("FFF");
        // System.out.println(solution.pourWater(heights, 5, 5));

        String bottom2 = "ABDFDGFDDEBGFFEDBCECEBAFCECEBEFBCDDDFDGFDGAEEBDECGDBBECABGDDAGDDAACFGDECGGCBDBGBCFCFECFEDDCEGBEBBCFA";
        String[] allowed2 = new String[] { "BGA", "BGB", "BGC", "BGD", "BGE", "BGF", "BGG", "EGC", "CCD", "CCG", "EGG",
                "EGF", "EGE", "EGD", "ADD", "DCF", "DCD", "DCB", "DCA", "FGD", "FGF", "FGB", "BFB", "BFG", "BFF", "BFE",
                "DDE", "CDA", "FAA", "DBA", "DBC", "DBB", "DBD", "DBG", "DBF", "FFG", "FFF", "FFE", "FFD", "BDE", "FFA",
                "GBG", "GBE", "FDE", "GBC", "FDG", "FDF", "BEB", "BEA", "BEG", "AEA", "CCE", "GCB", "AEE", "AED", "AEG",
                "AEF", "EEE", "GCE", "DEB", "DEC", "DED", "EGA", "EEC", "CEF", "CEE", "CED", "CEC", "GCG", "GDA", "GEC",
                "GEB", "GEG", "GEF", "GEE", "GED", "FBG", "CCB", "GDE", "GCA", "BDD", "BDG", "BDF", "EGB", "BDC", "GCF",
                "AFE", "GDG", "AFA", "AFC", "DGA", "ECC", "DDA", "EFC", "EFD", "DDF", "EFF", "EFG", "CBC", "CBA", "CBF",
                "CBD", "GBA", "ACA", "ACG", "ACF", "BCD", "BCG", "FDB", "GBB", "EFB", "DGB", "ECF", "ECE", "ECD", "DGF",
                "DGG", "ECA", "CGC", "CGB", "CGE", "CGG", "CGF", "GGE", "GGG", "GGF", "FCE", "GGC", "GGB", "CCC", "DDD",
                "FEF", "FED", "FEB", "FEC", "FEA", "BBG", "BBD", "EDB", "FCG", "ADB", "ADC", "DFE", "DFG", "DFF", "CDD",
                "CDE", "EDA", "EDG", "CDB", "CDC", "FBC", "FBB", "GDB", "GDD", "FBF", "FBE", "FBD", "AAD", "AAG", "AAA",
                "AAB", "FCC", "BAF", "BAG", "BAE", "BAB", "BAC", "CAB", "CAA", "CAF", "CAD", "DAD", "DAF", "EEF", "DAA",
                "DAB", "EAA", "EEA", "EAC", "EAB", "EAE", "EAD", "DEF", "FAC", "GAD", "GAC", "GAB", "GAA", "ABD", "ABE",
                "ABF", "EFE", "FAG", "ECG", "EBD", "EBA", "EBC", "CFD", "CFE", "CFB", "CFC", "CFA", "GFB", "GFC", "GFG",
                "GFD" };
        System.out.println(solution.pyramidTransition(bottom2, Arrays.asList(allowed2)));
    }
}
