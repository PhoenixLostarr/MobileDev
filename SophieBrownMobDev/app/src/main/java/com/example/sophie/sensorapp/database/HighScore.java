package com.example.sophie.sensorapp.database;

/**
 * Created by sophie on 08/12/16.
 *
 * Data Transfer Object for score data.
 */

public class HighScore
{
    private String difficulty;
    private int score;

    public HighScore(int score, String difficulty) {
        this.score = score;
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
