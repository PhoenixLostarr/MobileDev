package com.example.sophie.sensorapp.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.sophie.sensorapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sophie on 08/12/16.
 *
 * This class implements the adapter pattern for displaying a list of high scores in a list view.
 */

public class DatabaseAdapter extends ArrayAdapter {
    private ArrayList<HighScore> scores;
    private LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    public DatabaseAdapter(Context context, int resource, List<HighScore> objects) {
        super(context, resource, objects);
        scores = (ArrayList<HighScore>)objects;
    }

    @NonNull
    @Override
    /**
     * set up each highscore view and set the values
     */
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            convertView=inflater.inflate(R.layout.highscore_view, null);
        }

        HighScore score = scores.get(position);
        ((TextView)convertView.findViewById(R.id.lbDifficulty)).setText(score.getDifficulty());
        TextView scoreView = ((TextView)convertView.findViewById(R.id.lbScore));
        scoreView.setText(Integer.toString(score.getScore()));
        return convertView;
    }
}
