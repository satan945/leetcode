/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abel created on 2018/1/16 下午9:46
 * @version $Id$
 */
public class LRUCache {
    private Map<Integer, DoubleLinkedNode> map;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;
    private int maxSize;

    class DoubleLinkedNode {
        public int key;
        public int val;
        public DoubleLinkedNode pre;
        public DoubleLinkedNode next;

        public DoubleLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.maxSize = capacity;
        head = new DoubleLinkedNode(0, 0);
        tail = new DoubleLinkedNode(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DoubleLinkedNode node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        int curSize = map.size();
        DoubleLinkedNode node = new DoubleLinkedNode(key, value);
        if (map.containsKey(key)) {
            DoubleLinkedNode existNode = map.get(key);
            map.remove(key);
            removeNode(existNode);
            addNode(node);
            map.put(key, node);
        } else {
            if (curSize == maxSize) {
                map.remove(tail.pre.key);
                removeNode(tail.pre);
            }
            map.put(node.key, node);
            addNode(node);
        }
    }

    private void addNode(DoubleLinkedNode node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private void removeNode(DoubleLinkedNode node) {
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 6);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
}
