package com.example.projectzombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        //Context context = getActivity();
        //SharedPreferences sharedPref = context.getSharedPreferences(getString(R.integer.preference_file_key),Context.MODE_PRIVATE);


        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);


        TextView textView = findViewById(R.id.ScoreButton);

        String scoreText = "Score: " + score;
        textView.setText(scoreText);


        final Button button = (Button) findViewById(R.id.playAgainBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}