package se.umu.cs.android.thirty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

/**
 * This activity has the purpose of showing the score at the end of a game.
 */

public class ScoreActivity extends AppCompatActivity {

    private static final String EXTRA_SCORE_MAP = "score_map";
    private HashMap<PointOptions, Integer> mPoints;
    private TextView lowScore;
    private TextView fourScore;
    private TextView fiveScore;
    private TextView sixScore;
    private TextView sevenScore;
    private TextView eightScore;
    private TextView nineScore;
    private TextView tenScore;
    private TextView elevenScore;
    private TextView twelveScore;
    private TextView totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mPoints = (HashMap<PointOptions,Integer>) getIntent().getSerializableExtra(EXTRA_SCORE_MAP);

        lowScore = (TextView) findViewById(R.id.low_points);
        lowScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.LOW)));
        fourScore = (TextView) findViewById(R.id.four_points);
        fourScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.FOUR)));
        fiveScore = (TextView) findViewById(R.id.five_points);
        fiveScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.FIVE)));
        sixScore = (TextView) findViewById(R.id.six_points);
        sixScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.SIX)));
        sevenScore = (TextView) findViewById(R.id.seven_points);
        sevenScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.SEVEN)));
        eightScore = (TextView) findViewById(R.id.eight_points);
        eightScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.EIGHT)));
        nineScore = (TextView) findViewById(R.id.nine_points);
        nineScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.NINE)));
        tenScore = (TextView) findViewById(R.id.ten_points);
        tenScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.TEN)));
        elevenScore = (TextView) findViewById(R.id.eleven_points);
        elevenScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.ELEVEN)));
        twelveScore = (TextView) findViewById(R.id.twelve_points);
        twelveScore.setText(String.format(Locale.ENGLISH, "%d",mPoints.get(PointOptions.TWELVE)));

        int total = 0;
        for (Integer points : mPoints.values()) {
            total += points;
        }
        totalScore = (TextView) findViewById(R.id.total_points);
        totalScore.setText(String.format(Locale.ENGLISH, "%d",total));
    }

    /**
     * Creates an intent which is designed for this activity.
     * @param packageContext The context which wants to start this activity.
     * @param points The score from the game that has been played.
     * @return The finished intent.
     */
    public static Intent newIntent(Context packageContext, HashMap<PointOptions, Integer> points) {
        Intent intent = new Intent(packageContext, ScoreActivity.class);
        intent.putExtra(EXTRA_SCORE_MAP, points);

        return intent;
    }
}
