package se.umu.cs.android.thirty;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * This class represents a die that can be used in a game, and be in a state of saved where the
 * die won't be rolled.
 */

public class Dice implements Serializable {
    private int mCurrentValue;
    private boolean mIsSaved;

    public Dice() {
        rollDie();
        mIsSaved = false;
    }

    /**
     * Will roll the die if it's not saved.
     */
    public void rollDie() {
        if (!mIsSaved) {
            mCurrentValue = new Random().nextInt(6) + 1;
        }
    }

    /**
     *
     * @return The value of the die
     */
    public int getCurrentValue() {
        return mCurrentValue;
    }

    /**
     *
     * @return True if the die is saved, else false
     */
    public boolean isSaved() {
        return mIsSaved;
    }

    /**
     * Toggles the die as saved or not saved.
     */
    public void setSaved() {
        mIsSaved = !mIsSaved;
    }

    /**
     *
     * @return The id with the image corresponding to the dies state.
     */
    public int getImage() {
        switch (mCurrentValue) {
            case 1:
                return mIsSaved ? R.drawable.grey1 : R.drawable.white1;
            case 2:
                return mIsSaved ? R.drawable.grey2 : R.drawable.white2;
            case 3:
                return mIsSaved ? R.drawable.grey3 : R.drawable.white3;
            case 4:
                return mIsSaved ? R.drawable.grey4 : R.drawable.white4;
            case 5:
                return mIsSaved ? R.drawable.grey5 : R.drawable.white5;
            case 6:
                return mIsSaved ? R.drawable.grey6 : R.drawable.white6;
            default:
                return 0;
        }
    }

    /**
     * Required for the die to be serializable
     * @param aInputStream
     */
    private void readObject(ObjectInputStream aInputStream) {
        try {
            aInputStream.defaultReadObject();
        } catch (ClassNotFoundException | IOException e) {
            Log.e("Dice", "readObject caused exception", e);
        }
    }

    /**
     * Required for the die to be serializable
     * @param aOutputStream
     */
    private void writeObject(ObjectOutputStream aOutputStream) {
        try {
            aOutputStream.defaultWriteObject();
        } catch (IOException e) {
            Log.e("Dice", "Write Object caused exception", e);
        }
    }
}
