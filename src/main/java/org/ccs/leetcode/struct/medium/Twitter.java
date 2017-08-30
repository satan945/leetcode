/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.struct.medium;

import java.util.List;
import java.util.Map;

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

    Map<Integer, List<Integer>> followMap;

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {

    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        return null;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {

    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {

    }
    /**
     * Your Twitter object will be instantiated and called as such: Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId); List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId); obj.unfollow(followerId,followeeId);
     */
}
