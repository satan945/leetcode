/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.contest.weekly.contest68;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abel created on 2018/1/20 下午7:59
 * @version $Id$
 */
public class BasicCalculatorIV {
    Map<String, Integer> map;
    char[] s;
    int len, pos;

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        map = new HashMap<>();
        for (int i = 0; i < evalints.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }
        s = expression.replace(" ", "").toCharArray();
        len = s.length;
        pos = 0;
        List<Term> ts = clean(expr1());
        List<String> ret = new ArrayList<>();
        for (Term t : ts) {
            StringBuilder sb = new StringBuilder();
            sb.append(t.co);
            for (String v : t.ve) {
                sb.append("*" + v);
            }
            ret.add(sb.toString());
        }
        return ret;
    }

    class Term {
        long co;
        List<String> ve;

        public Term(long co, List<String> ve) {
            this.co = co;
            this.ve = ve;
        }
    }

    List<Term> clean(List<Term> ts) {
        for (Term t : ts) {
            Collections.sort(t.ve);
        }
        Collections.sort(ts, new Comparator<Term>() {
            public int compare(Term a, Term b) {
                if (a.ve.size() != b.ve.size()) {
                    return -(a.ve.size() - b.ve.size());
                }
                for (int i = 0; i < a.ve.size(); i++) {
                    int comp = a.ve.get(i).compareTo(b.ve.get(i));
                    if (comp != 0)
                        return comp;
                }
                return 0;
            }
        });
        List<Term> ret = new ArrayList<>();
        for (int i = 0; i < ts.size();) {
            int j = i;
            while (j < ts.size() && ts.get(j).ve.equals(ts.get(i).ve))
                j++;

            long cosum = 0;
            for (int k = i; k < j; k++)
                cosum += ts.get(k).co;
            if (cosum != 0) {
                ret.add(new Term(cosum, ts.get(i).ve));
            }

            i = j;
        }
        return ret;
    }

    Term mul(Term a, Term b) {
        List<String> ve = new ArrayList<>(a.ve);
        ve.addAll(b.ve);
        return new Term(a.co * b.co, ve);
    }

    List<Term> expr1() {
        List<Term> res = expr2();
        while (pos < len && (s[pos] == '+' || s[pos] == '-')) {
            char o = s[pos];
            pos++;
            List<Term> res2 = expr2();
            if (o == '+') {
                res.addAll(res2);
            } else {
                for (Term t : res2) {
                    t.co = -t.co;
                }
                res.addAll(res2);
            }
        }
        return clean(res);
    }

    List<Term> expr2() {
        List<Term> res = expr3();
        while (pos < len && (s[pos] == '*')) {
            char o = s[pos];
            pos++;
            List<Term> res2 = expr2();
            List<Term> nres = new ArrayList<>();
            for (Term t1 : res) {
                for (Term t2 : res2) {
                    nres.add(mul(t1, t2));
                }
            }
            res = clean(nres);
        }
        return res;
    }

    List<Term> expr3() {
        if (pos < len && s[pos] == '(') {
            pos++;
            List<Term> ret = expr1();
            pos++;
            return ret;
        } else if (s[pos] >= '0' && s[pos] <= '9' || s[pos] == '-') {
            long num = num();
            List<Term> ret = new ArrayList<>();
            ret.add(new Term(num, new ArrayList<>()));
            return ret;
        } else {
            String var = var();
            if (map.containsKey(var)) {
                long num = map.get(var);
                List<Term> ret = new ArrayList<>();
                ret.add(new Term(num, new ArrayList<>()));
                return ret;
            } else {
                List<Term> ret = new ArrayList<>();
                List<String> ve = new ArrayList<>();
                ve.add(var);
                ret.add(new Term(1, ve));
                return ret;
            }
        }
    }

    long num() {
        long x = 0;
        boolean sig = false;
        if (pos < len && s[pos] == '-') {
            sig = true;
            pos++;
        }
        while (pos < len && s[pos] >= '0' && s[pos] <= '9') {
            x = x * 10 + s[pos] - '0';
            pos++;
        }
        return sig ? -x : x;
    }

    String var() {
        StringBuilder sb = new StringBuilder();
        while (pos < len && s[pos] >= 'a' && s[pos] <= 'z') {
            sb.append(s[pos++]);
        }
        return sb.toString();
    }
}
