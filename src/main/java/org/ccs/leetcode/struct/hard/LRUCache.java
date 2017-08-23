/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.struct.hard;

/**
 * @author abel created on 2017/8/22 下午6:18
 * @version $Id$
 */

import java.util.HashMap;

/**
 * 146. LRU Cache
 * <p>
 * https://leetcode.com/problems/lru-cache
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 * 
 * Follow up: Could you do both operations in O(1) time complexity?
 * </p>
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 */
public class LRUCache {

    public HashMap<Integer, DoubleLinkedNode> map;
    public int capacity;
    public int count;
    public DoubleLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DoubleLinkedNode(0, 0);
        tail = new DoubleLinkedNode(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }

    public void delNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(DoubleLinkedNode node) {
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DoubleLinkedNode doubleLinkedNode = map.get(key);
            int res = doubleLinkedNode.value;
            delNode(doubleLinkedNode);
            addToHead(doubleLinkedNode);
            return res;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoubleLinkedNode node = map.get(key);
            node.value = value;
            delNode(node);
            addToHead(node);
        } else {
            DoubleLinkedNode node = new DoubleLinkedNode(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
            } else {
                map.remove(tail.pre.key);
                delNode(tail.pre);
            }
            addToHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
    }
}
