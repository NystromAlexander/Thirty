package se.umu.cs.android.thirty;

/**
 * Created by dv15anm on 2017-06-19.
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

    private PointOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
