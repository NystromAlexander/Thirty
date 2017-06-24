package se.umu.cs.android.thirty;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by dv15anm on 2017-06-21.
 */

public class DiceOnClickListener implements View.OnClickListener {

    private GameHandler mGameHandler;
    private int mDieNr;

    public DiceOnClickListener(GameHandler gameHandler, int dieNr) {
        mGameHandler = gameHandler;
        mDieNr = dieNr;
    }

    @Override
    public void onClick(View v) {
        ImageButton die = (ImageButton) v;
        mGameHandler.setSavedDie(mDieNr);
        die.setBackgroundResource(mGameHandler.getDieImage(mDieNr));
        mGameHandler.choseDie(mDieNr);
    }
}
