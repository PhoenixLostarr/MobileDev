package com.example.sophie.sensorapp.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sophie.sensorapp.helpers.Difficulty;
import com.example.sophie.sensorapp.gameobjects.GameObject;
import com.example.sophie.sensorapp.gameobjects.Obstacle;
import com.example.sophie.sensorapp.gameobjects.ObstacleFactory;
import com.example.sophie.sensorapp.gameobjects.Player;
import com.example.sophie.sensorapp.gameobjects.Star;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by sophie on 10/11/16.
 *
 * GameView encapsulates all of the graphical drawing code into a single view class.
 */

public class GameView extends View
{
    private List<GameObject> objList;
    private ProgressBar healthBar;
    private String[] obstacles = {"asteroid","satellite"};

    Random r = new Random();
    Difficulty difficulty;


    Player player;
    //this is how far each star will move up the screen each event
    private float x,y;


    public GameView(Context context) {
        super(context);
        objList = new ArrayList<>();
    }

    public GameView(Context context, ProgressBar healthBar,Player player,Difficulty difficulty)
    {
        this(context);
        this.healthBar = healthBar;
        this.player = player;
        healthBar.setMax(player.getHealth());
        this.difficulty = difficulty;
        healthBar.setBackgroundColor(Color.GREEN);
        healthBar.setProgress(player.getHealth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.draw(canvas);
        Iterator<GameObject> it = objList.iterator();
        while(it.hasNext())
        {
            GameObject obj = it.next();

            obj.setDy(y);
            obj.move();
            obj.draw(canvas);
            if(obj instanceof Obstacle)
            {
                Obstacle obst = (Obstacle) obj;
                if(obst.isIntersectPlayer(player))
                {
                    int damage = obst.getDamage() * difficulty.getValue();
                    player.setHealth(player.getHealth() - damage);
                    healthBar.setProgress(player.getHealth());
                }
            }

            if(obj.getY() < 0)
            {
                it.remove();
            }
        }

    }

    public void createStar()
    {
        int width = this.getWidth();
        if(width == 0)
        return;

        int randX = r.nextInt(width - 10);
        Star star = new Star(randX);
        objList.add(star);
    }

    public void createObstacle(Context context)
    {
        int width = this.getWidth();
        if(width == 0)
            return;
        int randX = r.nextInt(width - 100);
        String obstacle = obstacles[r.nextInt(obstacles.length)];
        Obstacle o = ObstacleFactory.createObstacle(obstacle,context,randX);
        objList.add(o);
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }
}
