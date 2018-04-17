package com.example.robda.androidacw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class gameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
       // String layout = intent.getStringExtra("puzzleLayout");
        Toast.makeText(this, intent.getStringExtra("puzzleLayout"), Toast.LENGTH_SHORT).show();
        Log.i("GameActivity", intent.getStringExtra("puzzleLayout"));
    }
}
