package com.example.sophie.sensorapp.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sophie.sensorapp.helpers.Difficulty;
import com.example.sophie.sensorapp.R;
import com.example.sophie.sensorapp.database.DatabaseHelper;
import com.example.sophie.sensorapp.database.DatabaseStructureContract;

/**
 * This class displays the game's high score, and offers a choice of viewing the leaderboard or exiting.
 */
public class HighScoreActivity extends AppCompatActivity {

    @Override
    /**
     * setup method - instantiate buttons, display final score. Score is then uploaded to SQLite Scores table
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_high_score);
        Bundle extras = getIntent().getExtras();
        int score = extras.getInt(getString(R.string.scoreBundleKey));
        Difficulty difficulty = (Difficulty) extras.get(getString(R.string.difficultyBundleKey));
        score *= difficulty.getValue();
        Button exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //exit app
                //System.exit(0);
                finish();
            }
        });

        Button leaderboard = (Button) findViewById(R.id.highScoresButton);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //switch to leaderboard
                Intent intent = new Intent(HighScoreActivity.this,LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        TextView scoreView = (TextView) findViewById(R.id.highScore);

        scoreView.setText(getString(R.string.congratulations,score));
        ContentValues values = new ContentValues();
        values.put(DatabaseStructureContract.FeedEntry.HIGH_SCORE, score);
        values.put(DatabaseStructureContract.FeedEntry.DIFFICULTY, difficulty.toString());
        db.insert(DatabaseStructureContract.FeedEntry.TABLE_NAME,null,values);


    }
}
