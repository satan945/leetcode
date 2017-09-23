/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author abel created on 2017/8/29 下午5:12
 * @version $Id$
 */

/**
 * 355. Design Twitter
 * <p>
 * https://leetcode.com/problems/design-twitter
 * <p>
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see
 * the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 * 
 * postTweet(userId, tweetId): Compose a new tweet. getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the
 * user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself.
 * Tweets must be ordered from most recent to least recent. follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee. Example:
 * 
 * Twitter twitter = new Twitter();
 * 
 * // User 1 posts a new tweet (id = 5). twitter.postTweet(1, 5);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5]. twitter.getNewsFeed(1);
 * 
 * // User 1 follows user 2. twitter.follow(1, 2);
 * 
 * // User 2 posts a new tweet (id = 6). twitter.postTweet(2, 6);
 * 
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. // Tweet id 6 should precede tweet id 5
 * because it is posted after tweet id 5. twitter.getNewsFeed(1);
 * 
 * // User 1 unfollows user 2. twitter.unfollow(1, 2);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5], // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 * </p>
 */
public class Twitter {

    Map<Integer, Set<Integer>> followMap = new HashMap<>();
    Map<Integer, LinkedList<Tweet>> tweetMap = new HashMap<>();
    int time = 0;

    public class Tweet {
        public int time;
        public int id;

        public Tweet(int time, int id) {
            this.time = time;
            this.id = id;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!followMap.containsKey(userId)) {
            followMap.put(userId, new HashSet<>());
        }
        followMap.get(userId).add(userId);
        if (!tweetMap.containsKey(userId)) {
            tweetMap.put(userId, new LinkedList<>());
        }
        tweetMap.get(userId).push(new Tweet(++time, tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!followMap.containsKey(userId)) {
            return new LinkedList<>();
        }
        PriorityQueue<Tweet> feedQueue = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
        Set<Integer> userIdSet = followMap.get(userId);
        for (Map.Entry<Integer, LinkedList<Tweet>> entry : tweetMap.entrySet()) {
            if (userIdSet.contains(entry.getKey())) {
                feedQueue.addAll(entry.getValue());
            }
        }

        List<Integer> res = new LinkedList<>();
        while (feedQueue.size() > 0 && res.size() < 10) {
            res.add(feedQueue.poll().id);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) {
            followMap.put(followerId, new HashSet<>());
        }
        followMap.get(followerId).add(followeeId);

    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId || !followMap.containsKey(followerId)) {
            return;
        }
        followMap.get(followerId).remove(followeeId);

    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.getNewsFeed(1);
    }
    /**
     * Your Twitter object will be instantiated and called as such: Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId); List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId); obj.unfollow(followerId,followeeId);
     */
}
