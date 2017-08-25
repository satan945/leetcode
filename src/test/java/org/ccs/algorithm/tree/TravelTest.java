/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree;

import org.ccs.algorithm.tree.travel.InOrderTraversal;
import org.ccs.algorithm.tree.travel.PostOrderTraversal;
import org.ccs.algorithm.tree.travel.PreOrderTraversal;
import org.ccs.leetcode.bean.TreeNode;

import java.util.List;

/**
 * @author abel created on 2017/8/25 下午4:24
 * @version $Id$
 */
public class TravelTest {

    public static void main(String[] args) {

        InOrderTraversal inOrderTraversal = new InOrderTraversal();
        PreOrderTraversal preOrderTraversal = new PreOrderTraversal();
        PostOrderTraversal postOrderTraversal = new PostOrderTraversal();

        TreeNode root = buildTree();
        List<TreeNode> listInNR = inOrderTraversal.travelNR(root);
        List<TreeNode> listInR = inOrderTraversal.travelR(root);
        List<TreeNode> listPreR = preOrderTraversal.travelR(root);
        List<TreeNode> listPreNR = preOrderTraversal.travelNR(root);
        System.out.println(compareTwoNodeList(listInR, listInNR));
        System.out.println(compareTwoNodeList(listPreR, listPreNR));

    }

    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode ll = new TreeNode(3);
        TreeNode lr = new TreeNode(4);
        TreeNode lll = new TreeNode(5);
        TreeNode r = new TreeNode(6);
        TreeNode rr = new TreeNode(7);
        root.left = l;
        l.left = ll;
        ll.left = lll;
        l.right = lr;
        root.right = r;
        r.right = rr;
        return root;
    }

    private static boolean compareTwoNodeList(List<TreeNode> listR, List<TreeNode> listNR) {
        String strR = buildStrByNodeList(listR);
        String strNR = buildStrByNodeList(listNR);
        System.out.println("listR: " + strR);
        System.out.println("listNR: " + strNR);
        return strNR.equals(strNR);
    }

    private static String buildStrByNodeList(List<TreeNode> list) {
        StringBuilder sb = new StringBuilder();
        for (TreeNode node : list) {
            sb.append("->").append(node.val);
        }
        return sb.toString();
    }
}
