package com.example.sophie.sensorapp.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sophie on 10/11/16.
 *
 * Represents a star on the background. A Paint object is used to draw the star on the background.
 */

public class Star extends GameObject
{
    private Paint p;
    public Star(int x) {
        super(x);
        p = new Paint();
        p.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas)
    {
        //x will be randomly generated and will be fixed, y will periodically change based on
        //device angle
        canvas.drawRect(x, y, x+10,y+10,p);
    }
}
