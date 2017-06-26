package se.umu.cs.android.thirty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

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
        lowScore.setText(mPoints.get(PointOptions.LOW).toString());
        fourScore = (TextView) findViewById(R.id.four_points);
        fourScore.setText(mPoints.get(PointOptions.FOUR).toString());
        fiveScore = (TextView) findViewById(R.id.five_points);
        fiveScore.setText(mPoints.get(PointOptions.FIVE).toString());
        sixScore = (TextView) findViewById(R.id.six_points);
        sixScore.setText(mPoints.get(PointOptions.SIX).toString());
        sevenScore = (TextView) findViewById(R.id.seven_points);
        sevenScore.setText(mPoints.get(PointOptions.SEVEN).toString());
        eightScore = (TextView) findViewById(R.id.eight_points);
        eightScore.setText(mPoints.get(PointOptions.EIGHT).toString());
        nineScore = (TextView) findViewById(R.id.nine_points);
        nineScore.setText(mPoints.get(PointOptions.NINE).toString());
        tenScore = (TextView) findViewById(R.id.ten_points);
        tenScore.setText(mPoints.get(PointOptions.TEN).toString());
        elevenScore = (TextView) findViewById(R.id.eleven_points);
        elevenScore.setText(mPoints.get(PointOptions.ELEVEN).toString());
        twelveScore = (TextView) findViewById(R.id.twelve_points);
        twelveScore.setText(mPoints.get(PointOptions.TWELVE).toString());

        int total = 0;
//                 Integer[] pointArray = (Integer[]) mPoints.values().toArray();
        totalScore = (TextView) findViewById(R.id.total_points);
        totalScore.setText(((Integer)total).toString());
    }

    public static Intent newIntent(Context packageContext, HashMap<PointOptions, Integer> points) {
        Intent intent = new Intent(packageContext, ScoreActivity.class);
        intent.putExtra(EXTRA_SCORE_MAP, points);

        return intent;
    }
}
