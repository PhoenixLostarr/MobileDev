package com.example.sophie.sensorapp.gameobjects;

import android.graphics.drawable.Drawable;

/**
 * Created by sophie on 24/11/16.
 *
 * Concrete subclass of Obstacle.
 */

public class Satellite extends Obstacle
{

    public Satellite(int x, Drawable image)
    {
        super(x, image);
        damage = 100;
    }
}
