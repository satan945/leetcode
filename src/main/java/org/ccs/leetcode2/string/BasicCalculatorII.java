/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author abel created on 2018/3/14 下午9:15
 * @version $Id$
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        int num = 0;
        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length()) {
                if (opStack.isEmpty()) {
                    numStack.push(num);
                } else {
                    if (opStack.peek() == '-') {
                        opStack.pop();
                        numStack.push(cal(numStack.pop(), -num, '+'));
                    } else {
                        numStack.push(cal(numStack.pop(), num, opStack.pop()));
                    }
                }
                break;
            }
            char ch = s.charAt(i);
            if (!isLegal(ch)) {
                continue;
            }
            if (!Character.isDigit(ch)) {
                if (!opStack.isEmpty() && isHighPriority(opStack.peek())) {
                    num = cal(numStack.pop(), num, opStack.pop());
                } else if (!opStack.isEmpty() && opStack.peek() == '-') {
                    num = -num;
                    opStack.pop();
                    opStack.push('+');
                }
                numStack.push(num);
                opStack.push(ch);
                num = 0;
            } else {
                num = num * 10 + (ch - '0');
            }
        }
        while (!opStack.isEmpty()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.push(cal(num1, num2, opStack.pop()));
        }

        return numStack.pop();
    }

    private boolean isLegal(char ch) {
        return Character.isDigit(ch) || ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int cal(int num1, int num2, char op) {
        switch (op) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            return num1 / num2;
        default:
            return 0;
        }
    }

    private boolean isHighPriority(char op) {
        return op == '*' || op == '/';
    }

    public static void main(String[] args) {
        BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
        System.out.println(basicCalculatorII.calculate("0-2147483647"));
    }
}
