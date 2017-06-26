package se.umu.cs.android.thirty;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by dv15anm on 2017-06-21.
 */

public class ScoreOptionListener implements View.OnClickListener {
    private PointOptions mOption;
    private GameHandler mGameHandler;
    private Game mGame;

    public ScoreOptionListener(PointOptions option, GameHandler gameHandler, Game game) {
        mOption = option;
        mGameHandler = gameHandler;
        mGame = game;
    }

    @Override
    public void onClick(View v) {
        mGameHandler.setChosenOption(mOption);
        int receivedPoints = mGameHandler.endTurn(mOption);
        Toast.makeText(mGame, "You recieved: "+receivedPoints+" points",Toast.LENGTH_SHORT).show();
        Button pointButton = (Button) v;
        pointButton.setClickable(false);
        pointButton.setEnabled(false);

        if (mGameHandler.isGameFinished()) {
            mGame.endGame();
        }

    }
}
