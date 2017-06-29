package se.umu.cs.android.thirty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends AppCompatActivity {
    private static final String SAVED_ACTIVE_BUTTONS = "active_buttons";
    private static final int POINT_BUTTON_NR = 10;
    private static final int DICE_NR = 6;

    private List<ImageButton> mDiceButtons;
    private List<Button> mPointButtons;
    private Button mThrowButton;
    private GameHandler mGameHandler;
    private boolean[] mActiveButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameHandler = new GameHandler();
        mActiveButtons = new boolean[POINT_BUTTON_NR];
        initializeDieList();
        initializePointButtons();
        mThrowButton = (Button) findViewById(R.id.throw_button);
        mThrowButton.setOnClickListener(v -> {
            if(!mGameHandler.gotThrowsLeft()) {
                Toast.makeText(Game.this,"No throws left", Toast.LENGTH_SHORT).show();
            } else {
                mGameHandler.rollDice();
                updateButtons();
            }
        });

        updateButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtons();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mGameHandler.saveState(outState);
        outState.putBooleanArray(SAVED_ACTIVE_BUTTONS, mActiveButtons);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mGameHandler.restoreState(savedInstanceState);
        mActiveButtons = savedInstanceState.getBooleanArray(SAVED_ACTIVE_BUTTONS);
    }

    /**
     * Is called when the last turn has been played and will start the activity displaying score,
     * and reset the game so a new round can be played.
     */
    public void endGame() {
        HashMap<PointOptions, Integer> score = new HashMap<>(mGameHandler.getPoints());
        Intent intent = ScoreActivity
                .newIntent(this, score);
        mGameHandler.resetGame();
        startActivity(intent);
        for(int i = 0; i < POINT_BUTTON_NR; i++) {
            mActiveButtons[i] = true;
        }
    }

    /**
     * Toggles a score button as used or not used.
     * @param buttonNr The position of the button in the list and array.
     */
    public void setButtonUsed(int buttonNr) {
        mActiveButtons[buttonNr] = !mActiveButtons[buttonNr];
    }

    /**
     * Initialize all the dice buttons with a listener.
     */
    private void initializeDieList() {
        mDiceButtons = new ArrayList<>();
        mDiceButtons.add((ImageButton) findViewById(R.id.die1_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die2_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die3_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die4_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die5_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die6_button));

        for(int i = 0; i < DICE_NR; i++) {
            mDiceButtons.get(i).setOnClickListener(new DiceOnClickListener(mGameHandler, i));
        }
    }

    /**
     * Update the buttons views.
     */
    private void updateButtons() {
        for(int i = 0; i < DICE_NR; i++) {
            int dieImg =mGameHandler.getDieImage(i);
            mDiceButtons.get(i).setBackgroundResource(dieImg);
        }
        for(int j = 0; j < POINT_BUTTON_NR; j++) {
            mPointButtons.get(j).setEnabled(mActiveButtons[j]);
        }
    }

    /**
     * Initializes the score option buttons, with listeners, and initialize the array that keeps
     * track of which buttons have been used
     */
    private void initializePointButtons() {
        mPointButtons = new ArrayList<>();
        Button lowButton = (Button) findViewById(R.id.low_button);
        lowButton.setOnClickListener(new ScoreOptionListener(PointOptions.LOW, mGameHandler, this, 0));
        mPointButtons.add(lowButton);
        Button fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(new ScoreOptionListener(PointOptions.FOUR, mGameHandler, this, 1));
        mPointButtons.add(fourButton);
        Button fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new ScoreOptionListener(PointOptions.FIVE, mGameHandler, this, 2));
        mPointButtons.add(fiveButton);
        Button sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(new ScoreOptionListener(PointOptions.SIX, mGameHandler, this, 3));
        mPointButtons.add(sixButton);
        Button sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new ScoreOptionListener(PointOptions.SEVEN, mGameHandler, this, 4));
        mPointButtons.add(sevenButton);
        Button eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new ScoreOptionListener(PointOptions.EIGHT, mGameHandler, this, 5));
        mPointButtons.add(eightButton);
        Button nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new ScoreOptionListener(PointOptions.NINE, mGameHandler, this, 6));
        mPointButtons.add(nineButton);
        Button tenButton = (Button) findViewById(R.id.ten_button);
        tenButton.setOnClickListener(new ScoreOptionListener(PointOptions.TEN, mGameHandler, this, 7));
        mPointButtons.add(tenButton);
        Button elevenButton = (Button) findViewById(R.id.eleven_button);
        elevenButton.setOnClickListener(new ScoreOptionListener(PointOptions.ELEVEN, mGameHandler, this, 8));
        mPointButtons.add(elevenButton);
        Button twelveButton = (Button) findViewById(R.id.twelve_button);
        twelveButton.setOnClickListener(new ScoreOptionListener(PointOptions.TWELVE, mGameHandler, this, 9));
        mPointButtons.add(twelveButton);

        for(int i = 0; i < POINT_BUTTON_NR; i++) {
            mActiveButtons[i] = true;
        }
    }
}
