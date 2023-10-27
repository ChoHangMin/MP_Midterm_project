package com.example.mp_midterm_project;


import java.util.LinkedList;
import java.util.Queue;

public class TournamentTree {
    public TreeNode root; // Node's root node.
    public String [] playerNames; // player names array.

    public TournamentTree(String [] playerNames) {
        this.playerNames = playerNames;
        root = buildTree(1, playerNames.length);
    }

    public TreeNode buildTree(int start, int end) { // build full binary full. recursive function.
        // The bottom nodes have player names value. and other node's value is null.
        if (start == end) { // base case
            return new TreeNode(playerNames[start - 1]); // array index start 0. so start - 1
        }

        int mid = (start + end) / 2; // get middle point.

        TreeNode leftSubTree = buildTree(start, mid);
        TreeNode rightSubTree = buildTree(mid + 1, end);
        TreeNode parentTree = new TreeNode("empty"); // If is not bottom node. They are all null values.

        parentTree.left = leftSubTree;
        parentTree.right = rightSubTree;

        leftSubTree.parent = parentTree;
        rightSubTree.parent = parentTree;

        return parentTree;
    }

    public int getHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;

    }



}


