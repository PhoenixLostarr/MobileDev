package com.example.sophie.sensorapp.gameobjects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.sophie.sensorapp.R;

/**
 * Created by sophie on 24/11/16.
 *
 * Factory class for dynamic creation of Obstacle subclasses. Factory returns either an Asteroid
 * or a Satellite based on
 */

public class ObstacleFactory
{
    public static Obstacle createObstacle(String desc, Context context,int x)
    {
        Drawable image;
        switch(desc)
        {
            case "asteroid":
            {
                image = ContextCompat.getDrawable(context, R.drawable.asteroid);
                return new Asteroid(x,image);
            }
            case "satellite":
            {
                image = ContextCompat.getDrawable(context,R.drawable.satellite);
                return new Satellite(x,image);
            }
        }

        return null;
    }
}
