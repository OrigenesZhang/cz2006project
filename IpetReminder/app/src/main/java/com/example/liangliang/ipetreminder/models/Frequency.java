package com.example.liangliang.ipetreminder.models;

/**
 * This class is an enum type of frequency.
 * This class defines constant values for frquency including
 * DAY, WEEK and MONTH.
 */

public enum Frequency {
    DAY(0), WEEK(1), MONTH(2);

    int ordinal;

    /**
     * This method sets value for the constant.
     * @param ord
     */
    Frequency(int ord) {
        this.ordinal = ord;
    }

    /**
     * This method gets the value of constant.
     * @param ord
     * @return the ordinal of the constant
     */
    public static Frequency byOrdinal(int ord) {
        return Frequency.values()[ord];
    }

    /**
     * @return the string representation of the constant
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
