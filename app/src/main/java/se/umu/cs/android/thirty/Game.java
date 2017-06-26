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

    private GameHandler mGameHandler;
    private List<ImageButton> mDiceButtons;
    private List<Button> mPointButtons;
    private Button mThrowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameHandler = new GameHandler();
        initializeDieList();
        initializePointButtons();
        mThrowButton = (Button) findViewById(R.id.throw_button);
        mThrowButton.setOnClickListener(v -> {
            if(!mGameHandler.gotThrowsLeft()) {
                Toast.makeText(Game.this,"No throws left", Toast.LENGTH_SHORT).show();
            } else {
                mGameHandler.rollDice();
                updateDiceButtons();
            }
        });

        updateDiceButtons();
    }

    public void endGame() {
        Intent intent = ScoreActivity.newIntent(this, (HashMap<PointOptions,Integer>) mGameHandler.getPoints());
        startActivity(intent);
        mGameHandler.resetGame();
    }

    private void initializeDieList() {
        mDiceButtons = new ArrayList<>();
        mDiceButtons.add((ImageButton) findViewById(R.id.die1_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die2_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die3_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die4_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die5_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die6_button));

        for(int i = 0; i < 6; i++) {
            mDiceButtons.get(i).setOnClickListener(new DiceOnClickListener(mGameHandler, i));
        }
    }

    private void updateDiceButtons() {
        for(int i = 0; i < 6; i++) {
            int dieImg =mGameHandler.getDieImage(i);
            mDiceButtons.get(i).setBackgroundResource(dieImg);
        }
    }

    private void initializePointButtons() {
        mPointButtons = new ArrayList<>();
        Button lowButton = (Button) findViewById(R.id.low_button);
        lowButton.setOnClickListener(new ScoreOptionListener(PointOptions.LOW, mGameHandler, this));
        mPointButtons.add(lowButton);
        Button fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(new ScoreOptionListener(PointOptions.FOUR, mGameHandler, this));
        mPointButtons.add(fourButton);
        Button fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new ScoreOptionListener(PointOptions.FIVE, mGameHandler, this));
        mPointButtons.add(fiveButton);
        Button sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(new ScoreOptionListener(PointOptions.SIX, mGameHandler, this));
        mPointButtons.add(sixButton);
        Button sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new ScoreOptionListener(PointOptions.SEVEN, mGameHandler, this));
        mPointButtons.add(sevenButton);
        Button eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new ScoreOptionListener(PointOptions.EIGHT, mGameHandler, this));
        mPointButtons.add(eightButton);
        Button nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new ScoreOptionListener(PointOptions.NINE, mGameHandler, this));
        mPointButtons.add(nineButton);
        Button tenButton = (Button) findViewById(R.id.ten_button);
        tenButton.setOnClickListener(new ScoreOptionListener(PointOptions.TEN, mGameHandler, this));
        mPointButtons.add(tenButton);
        Button elevenButton = (Button) findViewById(R.id.eleven_button);
        elevenButton.setOnClickListener(new ScoreOptionListener(PointOptions.ELEVEN, mGameHandler, this));
        mPointButtons.add(elevenButton);
        Button twelveButton = (Button) findViewById(R.id.twelve_button);
        twelveButton.setOnClickListener(new ScoreOptionListener(PointOptions.TWELVE, mGameHandler, this));
        mPointButtons.add(twelveButton);

    }
}
