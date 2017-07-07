package se.umu.cs.android.thirty;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameHandler is the class representing the game Thirty and enforcing the rules of the game.
 */

public class GameHandler {
    private static final String SAVED_CHOSEN_DICE = "chosen_dice";
    private static final String SAVED_DICE = "saved_dice";
    private static final String SAVED_POINTS = "points";
    private static final String SAVED_THROW_COUNT = "current_throw";
    private static final String SAVED_TURN_COUNT = "current_turn";

    private static final int NUMBER_OF_DICE = 6;
    private static final int NUMBER_OF_TURNS = 10;
    private static final int NUMBER_OF_THROWS = 3;

    private List<Dice> mDice;
    private List<Dice> mChosenDice;
    private Map<PointOptions,Integer> mPoints;
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

    /**
     * Will roll all the dice in the list.
     */
    public void rollDice() {
        if(mCurrentThrow < NUMBER_OF_THROWS) {
            for(int i = 0; i < mDice.size(); i++) {
                mDice.get(i).rollDie();
            }
            mCurrentThrow++;
        }
    }

    /**
     *
     * @return True if there is still throws left this turn, else false
     */
    public boolean gotThrowsLeft() {
        return mCurrentThrow < NUMBER_OF_THROWS;
    }


    /**
     * Toggles a die as saved, if saved it won't be rolled.
     * @param dieNr The position of the die in the list.
     */
    public void setSavedDie(int dieNr) {
        if (mCurrentThrow != 0) { //Stops a player for saving dice before the first throw
            mDice.get(dieNr).setSaved();
        }
    }

    /**
     * Retrieves the image of a given die
     * @param dieNr The position of the die in the list
     * @return The id of the image.
     */
    public int getDieImage(int dieNr) {
        return mDice.get(dieNr).getImage();
    }

    /**
     * Saves a die for scoring if it already saved it will be removed, achieving a toggle effect.
     * @param dieNr The dice position in the list.
     */
    public void choseDie(int dieNr) {
        if(mDice.get(dieNr).isSaved()) {
            mChosenDice.add(mDice.get(dieNr));
        } else {
            mChosenDice.remove(mDice.get(dieNr));
        }

    }

    /**
     *
     * @return The current throw count.
     */
    public int getCurrentThrow() {
        return mCurrentThrow;
    }

    /**
     *
     * @return Number of dice that has been selected for points.
     */
    public int nrSelectedDice() {
        return mChosenDice.size();
    }

    /**
     * @return The Map containing the score
     */
    public Map<PointOptions, Integer> getPoints() {
        return mPoints;
    }

    /**
     * Ends a turn in the game calculating points and removing selected dice.
     * @param chosenOption The option selected for the points
     * @return the score achieved during the turn.
     */
    public int endTurn(PointOptions chosenOption) {
        calculatePoints(chosenOption);
        mCurrentTurn++;
        mCurrentThrow = 0;
        mChosenDice.clear();

        for(int i = 0; i < mDice.size(); i++) {
            Dice die = mDice.get(i);
            if (die.isSaved()) {
                die.setSaved();
            }
        }
        return mPoints.get(chosenOption);
    }

    /**
     * Resets the game to start values.
     */
    public void resetGame() {
        mCurrentTurn = 1;
        mPoints.clear();
        mCurrentThrow = 0;
        mChosenDice.clear();

        for (int i = 0; i < mDice.size(); i++) {
            Dice die = mDice.get(i);
            if (die.isSaved()) {
                die.setSaved();
            }
        }

    }

    /**
     *
     * @return True if the game is finished else false.
     */
    public boolean isGameFinished() {
        return mCurrentTurn > NUMBER_OF_TURNS;
    }

    /**
     * Saves the games current state into a bundle object.
     * @param bundle The bundle where the information should be saved.
     * @return The updated bundle.
     */
    public Bundle saveState(Bundle bundle) {

        ArrayList<Dice> diceList = new ArrayList<>(mDice);
        ArrayList<Dice>  chosenList = new ArrayList<>(mChosenDice);
        HashMap<PointOptions, Integer> points = new HashMap<>(mPoints);
        bundle.putSerializable(SAVED_DICE, diceList);
        bundle.putSerializable(SAVED_CHOSEN_DICE, chosenList);
        bundle.putSerializable(SAVED_POINTS, points);
        bundle.putInt(SAVED_THROW_COUNT, mCurrentThrow);
        bundle.putInt(SAVED_TURN_COUNT, mCurrentTurn);

        return bundle;
    }

    /**
     * Takes a bundle and restores the game to the state that was saved in the bundle
     * @param bundle the information stored in saveState
     *
     * The unchecked warning is suppressed since we know the objects are of the correct type
     */
    @SuppressWarnings("unchecked")
    public void restoreState(Bundle bundle) {
        mDice = (ArrayList<Dice>) bundle.getSerializable(SAVED_DICE);
        mChosenDice = (ArrayList<Dice>) bundle.getSerializable(SAVED_CHOSEN_DICE);
        mPoints = (HashMap<PointOptions, Integer>) bundle.getSerializable(SAVED_POINTS);
        mCurrentThrow = bundle.getInt(SAVED_THROW_COUNT);
        mCurrentTurn = bundle.getInt(SAVED_TURN_COUNT);
    }

    /**
     * Adds the required number of dice in the list of dice.
     */
    private void initializeDice() {
        for(int i = 0; i < NUMBER_OF_DICE; i++) {
            mDice.add(new Dice());
        }
    }

    /**
     * Calculates the points of the selected dice, will sum the dice in the order they were selected
     * @param value The chosen score option
     */
    private void calculatePoints(PointOptions value) {
        int points = 0;

        if (value == PointOptions.LOW) {
            points = calculateLow();
        } else {
            List<Dice> diceCopy = new ArrayList<>(mChosenDice);
            Dice dieValue;
            int diceComp = 0;
            while (!diceCopy.isEmpty()) {
                dieValue = diceCopy.remove(0);
                diceComp += dieValue.getCurrentValue();
                if (diceComp == value.getValue()) {
                    points += value.getValue();
                    diceComp = 0;
                }
            }
        }

        mPoints.put(value, points);
    }

    /**
     * In the case of the low option it will take all dice with a value bellow 4 and sum them.
     * @return the score for all dice lower than 4.
     */
    private int calculateLow() {
        int roundTotal = 0;

        for (int i = 0; i < mDice.size(); i++) {
            Dice die = mDice.get(i);
            if (die.getCurrentValue() <= PointOptions.LOW.getValue()) {
                roundTotal += die.getCurrentValue();
            }
        }

        return roundTotal;
    }

}
