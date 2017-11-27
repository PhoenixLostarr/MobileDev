package com.example.sophie.sensorapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.sophie.sensorapp.helpers.DefaultValuesHelper;
import com.example.sophie.sensorapp.helpers.Difficulty;
import com.example.sophie.sensorapp.gameobjects.Player;
import com.example.sophie.sensorapp.R;

/**
 * This class encapsulates the game views and calculates the update logic frame-on-frame within the
 * SensorEventListener class.
 */
public class GameActivity extends AppCompatActivity {

    private GameView v;

    private Player player;

    private int maxSpeed,minSpeed;
    private int frameCounter = 0; //for star counting
    private Difficulty difficulty;
    private int obstacleMult;
    private boolean flagSwitchIntent=true;
    private SensorManager sensorManager;
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        /**
         * This method acts as one half of the game loop, performing calculations and passing the results to
         * GameView to display the resulting objects on the screen in the right way
         */
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                if(player.getHealth() <= 0 && flagSwitchIntent)
                {
                    //switch intents to HighScoreActivity
                    Intent intent = new Intent(GameActivity.this, HighScoreActivity.class);
                    intent.putExtra(getString(R.string.scoreBundleKey), frameCounter);
                    intent.putExtra(getString(R.string.difficultyBundleKey),difficulty);
                    startActivity(intent);

                    flagSwitchIntent=false;
                    finish();
                    detachListener();
                }

                int threshold = 5;

                if(frameCounter % threshold == 0)
                {
                    v.createStar();
                }
                if(frameCounter % (obstacleMult*threshold) == 0)
                {
                    //create a random obstacle - use factory pattern
                    v.createObstacle(GameActivity.this);

                }
                int modulo = (frameCounter % 100);
                if( modulo == 0 && obstacleMult > 1)
                {
                    obstacleMult--;
                }
                frameCounter ++;
                float y= event.values[1] * difficulty.getValue();

                if(y > maxSpeed)
                {
                    y = maxSpeed;
                }
                else if(y < minSpeed)
                {
                    y = minSpeed;
                }

                v.setY(y);

                v.invalidate();
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        /**
         * Performs calculations to move the player around. Player position is then updated within GameView.
         */
        public boolean onTouch(View v, MotionEvent event) {

            float x = event.getX();
            player.setX(x);
            return true;
        }
    };

    @Override
    /**
     * activity setup method - set defaults, instantiate instances of interfaces, set the game
     * up to start.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        RelativeLayout myLayout;
        difficulty = (Difficulty) getIntent().getExtras().get(getString(R.string.difficultyBundleKey));
        if (difficulty != null)
        {
            maxSpeed = DefaultValuesHelper.DEFAULT_MAX_SPEED * difficulty.getValue();
            minSpeed = DefaultValuesHelper.DEFAULT_MIN_SPEED * difficulty.getValue();
            obstacleMult = DefaultValuesHelper.DEFAULT_OBSTACLE_MULTIPLIER - difficulty.getValue();
        }
        else
        {
            maxSpeed = DefaultValuesHelper.DEFAULT_MAX_SPEED;
            minSpeed = DefaultValuesHelper.DEFAULT_MIN_SPEED;
            obstacleMult = DefaultValuesHelper.DEFAULT_OBSTACLE_MULTIPLIER;

        }

        Sensor accelerometer;
        ProgressBar healthBar = (ProgressBar) findViewById(R.id.test);
        player = createPlayer(this);
        v=new GameView(this,healthBar,player,difficulty);

        myLayout = (RelativeLayout) findViewById(R.id.activity_game);
        myLayout.addView(v);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                listener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        v.setOnTouchListener(touchListener);

    }

    /**
     * for use once the game has ended only.
     */
    private void detachListener()
    {
        sensorManager.unregisterListener(listener);
    }

    public Player createPlayer(Context context)
    {
        Drawable playerImage = ContextCompat.getDrawable(context,R.drawable.satellite);
        return Player.getPlayer(playerImage);
    }
}
