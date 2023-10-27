package com.example.mp_midterm_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NodePairActivity extends AppCompatActivity {
    Button buttonNode1, buttonNode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_pair);

        buttonNode1 = findViewById(R.id.buttonNode1);
        buttonNode2 = findViewById(R.id.buttonNode2);

        Intent intent = getIntent();
        TreeNode[] parcelableExtra = (TreeNode[]) intent.getParcelableArrayExtra("pairNode");

        buttonNode1.setText(parcelableExtra[0].playerName);
        buttonNode2.setText(parcelableExtra[1].playerName);

        buttonNode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedNode", parcelableExtra[0]);

                // 결과를 설정하고 Activity를 종료
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });

        buttonNode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedNode", parcelableExtra[1]);

                // 결과를 설정하고 Activity를 종료
                setResult(RESULT_OK, resultIntent);
                finish();

            }

        });

    }
}
