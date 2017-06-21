package se.umu.cs.android.thirty;

import java.util.Random;

/**
 * This class represents a die that can be used in a game, and be in a state of saved where the
 * die won't be rolled.
 */

public class Dice {
    private int mCurrentValue;
    private boolean mIsSaved;

    public Dice() {
        rollDie();
        mIsSaved = false;
    }

    public void rollDie() {
        if (!mIsSaved) {
            mCurrentValue = new Random().nextInt(6) + 1;
        }
    }

    public int getCurrentValue() {
        return mCurrentValue;
    }

    public boolean isSaved() {
        return mIsSaved;
    }

    public void setSaved() {
        mIsSaved = !mIsSaved;
    }

    public int getImage() {
        switch (mCurrentValue) {
            case 1:
                if (mIsSaved){
                    return R.drawable.grey1;
                }
                return R.drawable.white1;
            case 2:
                if (mIsSaved){
                    return R.drawable.grey2;
                }
                return R.drawable.white2;
            case 3:
                if (mIsSaved){
                    return R.drawable.grey3;
                }
                return R.drawable.white3;
            case 4:
                if (mIsSaved){
                    return R.drawable.grey4;
                }
                return R.drawable.white4;
            case 5:
                if (mIsSaved){
                    return R.drawable.grey5;
                }
                return R.drawable.white5;
            case 6:
                if (mIsSaved){
                    return R.drawable.grey6;
                }
                return R.drawable.white6;
            default:
                return 0;
        }
    }
}
