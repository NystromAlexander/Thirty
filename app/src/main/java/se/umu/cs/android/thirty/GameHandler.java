package se.umu.cs.android.thirty;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameHandler is the class representing the game Thirty and enforcing the rules of the game.
 */

public class GameHandler {
    private static final int NUMBER_OF_DICE = 6;
    private static final int NUMBER_OF_TURNS = 10;
    private static final int NUMBER_OF_THROWS = 3;

    private List<Dice> mDice;
    private List<Dice> mChosenDice;
    private Map<PointOptions,Integer> mPoints;
    private PointOptions mChosenOption;
    private int mCurrentThrow;
    private int mCurrentTurn;

    public GameHandler() {
        mDice = new ArrayList<>();
        mChosenDice = new ArrayList<>();
        mPoints = new HashMap<>();
        mCurrentTurn = 1;
        mCurrentThrow = 0;
        initializeDice();
    }

    public void rollDice() {
        if(mCurrentThrow < NUMBER_OF_THROWS) {
            mDice.forEach(Dice::rollDie);
            mCurrentThrow++;
        }
    }

    public boolean gotThrowsLeft() {
        return mCurrentThrow < NUMBER_OF_THROWS;
    }

    public List<Dice> getDice() {
        return mDice;
    }

    public void setSavedDie(int dieNr) {
        if (mCurrentThrow != 0) { //Stops a player for saving dice before the first throw
            mDice.get(dieNr).setSaved();
        }
    }

    public int getDieImage(int dieNr) {
        return mDice.get(dieNr).getImage();
    }

    public void choseDie(int dieNr) {
        if(mDice.get(dieNr).isSaved()) {
            mChosenDice.add(mDice.get(dieNr));
            Log.d("GameHandler", "chosenDie was "+mDice.get(dieNr).getCurrentValue());
        } else {
            mChosenDice.remove(mDice.get(dieNr));
        }

    }

//    public void removeDie(int dieNr) {
//        mChosenDice.remove(mDice.get(dieNr));
//    }

    public Map<PointOptions, Integer> getPoints() {
        return mPoints;
    }

    public int endTurn(PointOptions chosenOption) {
        calculatePoints(chosenOption);
        mCurrentTurn++;
        mDice.forEach(dice -> {if (dice.isSaved())dice.setSaved();});
        mCurrentThrow = 0;
        mChosenDice.clear();

        return mPoints.get(chosenOption);
    }

    public void resetGame() {
        mCurrentTurn = 0;
        mPoints.clear();
        mCurrentThrow = 0;
        mChosenDice.clear();
        mDice.forEach(dice -> {if (dice.isSaved())dice.setSaved();});
    }

    public boolean isGameFinished() {
        return mCurrentTurn > NUMBER_OF_TURNS;
    }

    public boolean setChosenOption(PointOptions option) {
        if (mChosenOption == null ) {
            mChosenOption = option;
            return true;
        } else {
            return false;
        }
    }

    private void initializeDice() {
        for(int i = 0; i < NUMBER_OF_DICE; i++) {
            mDice.add(new Dice());
        }
    }

    private void calculatePoints(PointOptions value) {
        int points = 0;

        if (value == PointOptions.LOW) {
            points = calculateLow();
        } else {
            List<Dice> diceCopy = new ArrayList<>(mChosenDice);
            Log.d("GameHandler", "diceCopy size "+diceCopy.size());
            Dice dieValue;
            int diceComp = 0;
            while (!diceCopy.isEmpty()) {
                dieValue = diceCopy.remove(0);
                diceComp += dieValue.getCurrentValue();
                Log.d("GameHandler", "diceComp is "+diceComp);
                if (diceComp == value.getValue()) {
                    points += value.getValue();
                    diceComp = 0;
                }
            }
            if (!diceCopy.isEmpty()) {

            }
        }

        mPoints.put(value, points);
        Log.d("GameHandler", "Points received: "+points);
    }

    private int calculateLow() {
        int roundTotal = 0;

        for (Dice die: mDice) {
            if (die.getCurrentValue() <= PointOptions.LOW.getValue()) {
                roundTotal += die.getCurrentValue();
            }
        }

        return roundTotal;
    }

}
