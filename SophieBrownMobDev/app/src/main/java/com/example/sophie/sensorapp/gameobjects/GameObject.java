package com.example.sophie.sensorapp.gameobjects;
import android.graphics.Canvas;

import com.example.sophie.sensorapp.helpers.DefaultValuesHelper;

/**
 * Created by sophie on 10/11/16.
 *
 * abstract superclass for all vertically-moving game objects
 */

public abstract class GameObject
{

    protected float x;
    protected float y;

    protected float dy;

    public GameObject(int x)
    {
        this.x = x;
        this.y = DefaultValuesHelper.DEFAULT_Y_POSITION;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void move()
    {
        setY(y - dy);
    }

    abstract public void draw(Canvas canvas);




}
