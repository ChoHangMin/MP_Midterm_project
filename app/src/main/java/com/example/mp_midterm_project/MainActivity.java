package com.example.mp_midterm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String [] playerNames = {"a", "b", "c", "d"};
    TournamentTree test = new TournamentTree(playerNames); // 주석을 추가했음
}