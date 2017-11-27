package com.example.sophie.sensorapp.gameobjects;

import android.graphics.drawable.Drawable;

/**
 * Created by sophie on 22/11/16.
 *
 * Concrete subclass of Obstacle.
 */

public class Asteroid extends Obstacle
{

    public Asteroid(int x,Drawable image)
    {
        super(x,image);
        damage = 50;
    }


}
