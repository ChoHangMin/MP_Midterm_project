package com.example.mp_midterm_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.concurrent.Semaphore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Queue;


public class StartTournamentActivity extends AppCompatActivity {

    public static final int NODE_PAIR_REQUEST_CODE = 1;
    private final Semaphore semaphore = new Semaphore(0); // Initially, no permits are available


    String [] playerNames = {"a", "b", "c", "d"};

    TournamentTree testTree;

    // Add ActivityResultLauncher to communicate each Activity.
    private final ActivityResultLauncher<Intent> mNodePairResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    TreeNode selectedNode = data.getParcelableExtra("selectedNode");
                    selectedNode.parent.playerName = selectedNode.playerName;
                    // Handle the selected node here
                    // Resume the bfsTraversalByLevel loop
                    semaphore.release(); // This will allow bfsTraversalByLevel to continue
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tournament); // 필요한 레이아웃 리소스를 여기에 연결하세요.

        testTree = new TournamentTree(playerNames);

        int maxLevel = testTree.getHeight(testTree.root) + 1;

        for (int i = maxLevel; i >= 0; --i) {
            bfsTraversalByLevel(testTree.root,i);
        }
    }

    public void bfsTraversalByLevel(TreeNode root,int targetLevel) { // print targetLevel node's value
        if (root == null) return;
        int level = 0; // entire node perspective, bfs traversal starting level 0.

        Queue<TreeNode> queue = new LinkedList<>(); // bfs using queue.
        queue.add(root); // search from root node.

        while(!queue.isEmpty()) {
            int levelSize = queue.size();

            if (level == targetLevel) {
                TreeNode[] pairNode = new TreeNode[2];
                int j = 0;

                for (int i = 0; i < levelSize; ++i) {
                    TreeNode current = queue.poll();
                    pairNode[j] = current;

                    if (j == 1) {
                        Intent putIntent = new Intent(StartTournamentActivity.this, NodePairActivity.class);
                        putIntent.putExtra("pairNode", pairNode);

                        mNodePairResultLauncher.launch(putIntent);

                        try {
                            semaphore.acquire(); // This will block until release() is called
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        j = 0;
                    } else {
                        ++j;
                    }
                }

                return;
            }

            for (int i = 0; i < levelSize; ++i) {
                TreeNode current = queue.poll();

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }

            }

            ++level;

        }

    }



}
