/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms;

import java.util.Stack;

/**
 * @author abel created on 2018/2/26 下午11:27
 * @version $Id$
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if (s == null || s.length() == 0 || s.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char token = s.charAt(i);
            if (token == '{' || token == '(' || token == '[') {
                stack.push(token);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                switch (token) {
                case '}':
                    if (stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ')':
                    if (stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                }
            }

        }
        return stack.isEmpty();
    }
}
