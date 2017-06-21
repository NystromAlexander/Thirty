package se.umu.cs.android.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity {

    private GameHandler mGameHandler;
    private List<ImageButton> mDiceButtons;
    private Button mThrowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameHandler = new GameHandler();
        initializeDieList();

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

    private void initializeDieList() {
        mDiceButtons = new ArrayList<>();
        mDiceButtons.add((ImageButton) findViewById(R.id.die1_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die2_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die3_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die4_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die5_button));
        mDiceButtons.add((ImageButton) findViewById(R.id.die6_button));

        for(int i = 0; i < 6; i++) {
            mDiceButtons.get(i).setOnClickListener(new DiceOnClickListner(mGameHandler, i));
        }
    }

    private void updateDiceButtons() {
        for(int i = 0; i < 6; i++) {
            mDiceButtons.get(i).setBackgroundResource(mGameHandler.getDieImage(i));
        }
    }
}
