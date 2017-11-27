package com.example.sophie.sensorapp.database;

import android.provider.BaseColumns;

/**
 * Created by sophie on 02/12/16.
 *
 * Defines table name and table column values as per android docs. For more information,
 * see https://developer.android.com/training/basics/data-storage/databases.html
 */

public class DatabaseStructureContract
{
    private DatabaseStructureContract()
    {} //private constructor to ensure class is never instantiated

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Scores";
        public static final String HIGH_SCORE = "Score";
        public static final String DIFFICULTY = "Difficulty";
    }

}
