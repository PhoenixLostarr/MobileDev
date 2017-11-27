package com.example.sophie.sensorapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.sophie.sensorapp.helpers.Difficulty;
import com.example.sophie.sensorapp.R;

/**
 * Entry point for the application. Displays a difficulty selection screen.
 * A difficulty contains an integer multiplier for use in game calculations
 */
public class MainActivity extends AppCompatActivity {

    Button easyB, mediumB, hardB;
    Difficulty difficulty;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == easyB)
            {
                difficulty = Difficulty.EASY;
            }
            else if(v == mediumB)
            {
                difficulty = Difficulty.MEDIUM;
            }
            else if(v == hardB)
            {
                difficulty = Difficulty.HARD;
            }
            else
            {
                difficulty = Difficulty.ERROR;
            }
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra(getString(R.string.difficultyBundleKey), difficulty);
            startActivity(intent);
            finish();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        easyB = (Button) findViewById(R.id.easyButton);
        mediumB = (Button) findViewById(R.id.mediumButton);
        hardB = (Button) findViewById(R.id.hardButton);
        easyB.setOnClickListener(listener);
        mediumB.setOnClickListener(listener);
        hardB.setOnClickListener(listener);
    }
}
