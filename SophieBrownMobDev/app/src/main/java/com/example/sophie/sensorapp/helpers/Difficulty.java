package com.example.sophie.sensorapp.helpers;

/**
 * Created by sophie on 07/12/16.
 *
 * Defines difficulty multipliers for use in the game calculations.
 */

public enum Difficulty
{

    EASY(1),
    MEDIUM(3),
    HARD(5),

    ERROR(1);

    private int value;

    Difficulty(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case EASY:
            {
                return "Easy";
            }
            case MEDIUM:
            {
                return "Medium";
            }
            case HARD:
            {
                return "Hard";
            }
            case ERROR:
            {
                return "Error: no difficulty available";
            }

        }
        return null;
    }



}
