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

    public ScoreOptionListener(PointOptions option, GameHandler gameHandler) {
        mOption = option;
        mGameHandler = gameHandler;
    }

    @Override
    public void onClick(View v) {
        mGameHandler.setChosenOption(mOption);
        mGameHandler.endTurn(mOption);
        Button pointButton = (Button) v;
        pointButton.setClickable(false);
        pointButton.setEnabled(false);

    }
}
