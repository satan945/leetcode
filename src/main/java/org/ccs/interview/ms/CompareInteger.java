/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms;

/**
 * OTS-01
 * 
 * @author abel created on 2018/1/17 上午11:06
 * @version $Id$
 */
public class CompareInteger {

    public static class Node {
        public char val;
        public Node next;

        public Node() {
        }

        public Node(char val) {
            this.val = val;
        }
    }

    public Node compare(Node list1, Node list2) {
        Node node1 = list1;
        Node node2 = list2;
        Node res = new Node();
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.val == '-' && node2.val != '-') {
            return node2;
        }
        if (node1.val != '-' && node2.val == '-') {
            return node1;
        }
        int num1 = buildNum(node1);
        int num2 = buildNum(node2);
        return num1 > num2 ? list1 : list2;

    }

    private int buildNum(Node node) {
        int res = 0;
        boolean negative = false;
        if (node.val == '-') {
            node = node.next;
            negative = true;
        }
        while (node != null) {
            res = res * 10 + (node.val - '0');
            node = node.next;
        }
        return negative ? -res : res;
    }

    public static void main(String[] args) {
        Node node1 = new Node('-');
        Node node11 = new Node('1');
        Node node111 = new Node('0');
        Node node1111 = new Node('1');
        node1.next = node11;
        node11.next = node111;
        node111.next = node1111;
        Node node2 = new Node('-');
        Node node21 = new Node('1');
        Node node211 = new Node('0');
        Node node2111 = new Node('0');
        node2.next = node21;
        node21.next = node211;
        node211.next = node2111;

        CompareInteger compareInteger = new CompareInteger();
        Node res = compareInteger.compare(node1, node2);
        System.out.println(res);

    }

}
