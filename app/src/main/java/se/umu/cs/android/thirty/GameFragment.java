package se.umu.cs.android.thirty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameFragment extends Fragment {
    private static final String SAVED_ACTIVE_BUTTONS = "active_buttons";
    private static final int POINT_BUTTON_NR = 10;
    private static final int DICE_NR = 6;

    private List<ImageButton> mDiceButtons;
    private List<Button> mPointButtons;
    private Button mThrowButton;
    private GameHandler mGameHandler;
    private boolean[] mActiveButtons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Set up the UI and if there is a saved state restore that state to the fragment and the
     * GameHandler.
     * @param inflater Used to inflate the UI elements.
     * @param container The container that will host the fragment.
     * @param savedInstanceState Contains the saved data of the fragment
     * @return The finished view.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game,container,false);

        mGameHandler = new GameHandler();
        mActiveButtons = new boolean[POINT_BUTTON_NR];
        initializeDieList(view);
        initializePointButtons(view);
        mThrowButton = (Button) view.findViewById(R.id.throw_button);
        mThrowButton.setOnClickListener(v -> {
            if(!mGameHandler.gotThrowsLeft()) {
                Toast.makeText(getActivity(),"No throws left", Toast.LENGTH_SHORT).show();
            } else {
                mGameHandler.rollDice();
                updateButtons();
            }
        });

        if (savedInstanceState != null) {
            mGameHandler.restoreState(savedInstanceState);
            mActiveButtons = savedInstanceState.getBooleanArray(SAVED_ACTIVE_BUTTONS);
        }

        updateButtons();

        return view;
    }

    /**
     * When the fragment is resumed update the buttons to make sure they are in the correct state.
     */
    @Override
    public void onResume() {
        super.onResume();
        updateButtons();
    }

    /**
     * Saves the current state of the fragment, also saves the state of the game.
     * @param outState Contains the stored data.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState = mGameHandler.saveState(outState);
        outState.putBooleanArray(SAVED_ACTIVE_BUTTONS, mActiveButtons);
    }


    /**
     * Is called when the last turn has been played and will start the activity displaying score,
     * and reset the game so a new round can be played.
     */
    public void endGame() {
        HashMap<PointOptions, Integer> score = new HashMap<>(mGameHandler.getPoints());
        Intent intent = ScoreActivity
                .newIntent(getActivity(), score);
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
    private void initializeDieList(View view) {
        mDiceButtons = new ArrayList<>();
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die1_button));
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die2_button));
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die3_button));
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die4_button));
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die5_button));
        mDiceButtons.add((ImageButton) view.findViewById(R.id.die6_button));

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
    private void initializePointButtons(View view) {
        mPointButtons = new ArrayList<>();
        Button lowButton = (Button) view.findViewById(R.id.low_button);
        lowButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.LOW, mGameHandler, this, 0));
        mPointButtons.add(lowButton);
        Button fourButton = (Button) view.findViewById(R.id.four_button);
        fourButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.FOUR, mGameHandler, this, 1));
        mPointButtons.add(fourButton);
        Button fiveButton = (Button) view.findViewById(R.id.five_button);
        fiveButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.FIVE, mGameHandler, this, 2));
        mPointButtons.add(fiveButton);
        Button sixButton = (Button) view.findViewById(R.id.six_button);
        sixButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.SIX, mGameHandler, this, 3));
        mPointButtons.add(sixButton);
        Button sevenButton = (Button) view.findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.SEVEN, mGameHandler, this, 4));
        mPointButtons.add(sevenButton);
        Button eightButton = (Button) view.findViewById(R.id.eight_button);
        eightButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.EIGHT, mGameHandler, this, 5));
        mPointButtons.add(eightButton);
        Button nineButton = (Button) view.findViewById(R.id.nine_button);
        nineButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.NINE, mGameHandler, this, 6));
        mPointButtons.add(nineButton);
        Button tenButton = (Button) view.findViewById(R.id.ten_button);
        tenButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.TEN, mGameHandler, this, 7));
        mPointButtons.add(tenButton);
        Button elevenButton = (Button) view.findViewById(R.id.eleven_button);
        elevenButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.ELEVEN, mGameHandler, this, 8));
        mPointButtons.add(elevenButton);
        Button twelveButton = (Button) view.findViewById(R.id.twelve_button);
        twelveButton.setOnClickListener(
                new ScoreOptionListener(PointOptions.TWELVE, mGameHandler, this, 9));
        mPointButtons.add(twelveButton);

        for(int i = 0; i < POINT_BUTTON_NR; i++) {
            mActiveButtons[i] = true;
        }
    }
}
