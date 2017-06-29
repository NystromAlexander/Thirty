package se.umu.cs.android.thirty;

import android.view.View;
import android.widget.ImageButton;

/**
 * OnClickListener for dice buttons
 */

public class DiceOnClickListener implements View.OnClickListener {

    private GameHandler mGameHandler;
    private int mDieNr;

    public DiceOnClickListener(GameHandler gameHandler, int dieNr) {
        mGameHandler = gameHandler;
        mDieNr = dieNr;
    }

    /**
     * When a dice button is clicked it will toggle the dice as saved, and update the image
     * corresponding to that state
     * @param v
     */
    @Override
    public void onClick(View v) {
        ImageButton die = (ImageButton) v;
        mGameHandler.setSavedDie(mDieNr);
        die.setBackgroundResource(mGameHandler.getDieImage(mDieNr));
        mGameHandler.choseDie(mDieNr);
    }
}
