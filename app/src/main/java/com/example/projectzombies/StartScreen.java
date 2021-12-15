package com.example.projectzombies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        final Button button = findViewById(R.id.playBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            new GameActivity();
            }
        });
    }

}