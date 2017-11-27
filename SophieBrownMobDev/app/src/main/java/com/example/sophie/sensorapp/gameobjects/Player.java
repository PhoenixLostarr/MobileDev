package com.example.sophie.sensorapp.gameobjects;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by sophie on 24/11/16.
 *
 * Controlled by the player's touch - listener for touch is in GameActivity.
 *
 * While logically a GameObject, this class moves in a radically different way to the rest
 * of the hierarchy - along the X axis as opposed to up the Y axis - and values are updated using a
 * different method - touch listener instead of sensor listener.
 */

public class Player
{
    private float x,y;
    private Drawable image;
    private static Player instance;

    private int health = 5000;

    private Player(Drawable image)
    {
        this.image = image;

        y = 100;
        x = 500;
    }

    public static Player getPlayer(Drawable image)
    {
        if(instance == null)
        {
            instance = new Player(image);

        }
        return instance;
    }

    public void draw(Canvas canvas)
    {
        image.setBounds((int)x,(int)y,(int)x+100,(int)y+100);
        image.draw(canvas);
    }

    public Drawable getImage()
    {
        return image;
    }

    public void setX(float x)
    {
        this.x = x;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
