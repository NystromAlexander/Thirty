package se.umu.cs.android.thirty;

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
        mDice.get(dieNr).setSaved();
    }

    public int getDieImage(int dieNr) {
        return mDice.get(dieNr).getImage();
    }

    public void choseDie(int dieNr) {
        mChosenDice.add(mDice.get(dieNr));
    }

    public void removeDie(int dieNr) {
        mChosenDice.remove(mDice.get(dieNr));
    }

    public Map<PointOptions, Integer> getPoints() {
        return mPoints;
    }

    public void endTurn(PointOptions chosenOption) {
        calculatePoints(chosenOption);
        mCurrentTurn++;
        mDice.forEach(Dice::setSaved);
    }

    public boolean isGameFinished() {
        return mCurrentTurn > NUMBER_OF_TURNS;
    }

    public boolean setChosenOption(PointOptions option) {
        if (mChosenOption == null) {
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
        if (value == PointOptions.LOW) {
            mPoints.put(value, calculateLow());
        }
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
