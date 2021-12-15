package com.example.projectzombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class EndScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.integer.preference_file_key),Context.MODE_PRIVATE);

    }
}