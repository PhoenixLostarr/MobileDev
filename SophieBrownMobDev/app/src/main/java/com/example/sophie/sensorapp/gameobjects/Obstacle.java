package com.example.sophie.sensorapp.gameobjects;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by sophie on 10/11/16.
 *
 * abstract superclass for game objects to avoid.
 */

public abstract class Obstacle extends GameObject
{
    private Drawable image;
    protected int damage;

    public Obstacle(int x,Drawable image)
    {
        super(x);
        this.image = image;
    }
    @Override
    public void draw(Canvas canvas)
    {
        image.setBounds((int)x,(int)y,(int)x+100,(int)y+100);
        image.draw(canvas);
    }

    public boolean isIntersectPlayer(Player player)
    {
        Drawable playerImg = player.getImage();
        return playerImg.getBounds().intersects((int)x,(int)y,(int)x+100,(int)y+100);
    }

    public int getDamage() {
        return damage;
    }
}
