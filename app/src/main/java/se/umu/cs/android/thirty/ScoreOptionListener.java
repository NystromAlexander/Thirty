package se.umu.cs.android.thirty;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * OnClickListener for the score buttons.
 */

public class ScoreOptionListener implements View.OnClickListener {
    private PointOptions mOption;
    private GameHandler mGameHandler;
    private Game mGame;
    private int mButtonNr;

    public ScoreOptionListener(PointOptions option, GameHandler gameHandler, Game game, int number) {
        mOption = option;
        mGameHandler = gameHandler;
        mGame = game;
        mButtonNr = number;
    }

    /**
     * When the button is pressed, it will check if the requirements are met, if so it will end the
     * turn and calculate the points received and show them in a toast.
     * If it was the last turn the game will end.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (mGameHandler.getCurrentThrow() != 0) {
            if (mGameHandler.nrSelectedDice() < 1) {
                Toast.makeText(mGame, "You have not selected any dice", Toast.LENGTH_SHORT).show();
                return;
            }
            int receivedPoints = mGameHandler.endTurn(mOption);
            Toast.makeText(mGame, "You received: " + receivedPoints + " points", Toast.LENGTH_SHORT)
                    .show();
            Button pointButton = (Button) v;
            pointButton.setEnabled(false);
            mGame.setButtonUsed(mButtonNr);
            if (mGameHandler.isGameFinished()) {
                mGame.endGame();
            }
        }
    }
}
