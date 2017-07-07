package se.umu.cs.android.thirty;

/**
 * Enumerator representing the different score options and keeps track of the
 * values for each option.
 */

public enum PointOptions {
    LOW(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12);

    private final int value;

    PointOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
