/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.stack;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 * 
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * 
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * </p>
 * 
 * @author abel created on 2018/1/16 下午6:19
 * @version $Id$
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> numStack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int op2 = numStack.pop();
                int op1 = numStack.pop();
                switch (token) {
                case "+":
                    numStack.push(op1 + op2);
                    break;
                case "-":
                    numStack.push(op1 - op2);
                    break;
                case "*":
                    numStack.push(op1 * op2);
                    break;
                case "/":
                    numStack.push(op1 / op2);
                    break;
                }
            } else {
                numStack.push(Integer.parseInt(token));
            }
        }
        return numStack.pop();

    }
}
