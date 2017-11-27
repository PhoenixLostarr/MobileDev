package com.example.sophie.sensorapp.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sophie.sensorapp.R;
import com.example.sophie.sensorapp.database.DatabaseAdapter;
import com.example.sophie.sensorapp.database.DatabaseHelper;
import com.example.sophie.sensorapp.database.DatabaseStructureContract;
import com.example.sophie.sensorapp.database.HighScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sophie on 08/12/16.
 *
 * This class gets a list of high scores from the database and displays the top 10 in a list.
 */
public class LeaderboardActivity extends AppCompatActivity {

    DatabaseAdapter adapter;

    @Override
    /**
     * setup method - fetches scores from database, processes them into a List<HighScore> then
     * uses an adapter to display them
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectHighestScores = "SELECT * FROM " + DatabaseStructureContract.FeedEntry.TABLE_NAME +
                " ORDER BY " + DatabaseStructureContract.FeedEntry.HIGH_SCORE +" DESC LIMIT 10";
        Cursor cursor = db.rawQuery(selectHighestScores,null);
        List<HighScore> scoreList = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                String difficulty = cursor.getString(2);
                int score = cursor.getInt(1);
                HighScore hs = new HighScore(score,difficulty);
                scoreList.add(hs);
            }while(cursor.moveToNext());
        }
        cursor.close();

        adapter = new DatabaseAdapter(this, R.id.activity_leaderboard,scoreList);
        ListView lview = (ListView) findViewById(R.id.highScoreView);
        lview.setAdapter(adapter);
    }
}
