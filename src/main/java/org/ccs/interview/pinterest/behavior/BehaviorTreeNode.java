/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.pinterest.behavior;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abel created on 2018/1/30 下午4:34
 * @version $Id$
 */
public class BehaviorTreeNode {
    public String action;
    public int count;
    Map<String, BehaviorTreeNode> postAction;

    public BehaviorTreeNode() {
        postAction = new HashMap<>();
    }
}
