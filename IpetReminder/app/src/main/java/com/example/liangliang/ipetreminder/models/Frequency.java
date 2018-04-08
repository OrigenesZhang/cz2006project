package com.example.liangliang.ipetreminder.models;

/**
 * Created by caoliu on 7/4/18.
 */

public enum Frequency {
    DAY(0), WEEK(1), MONTH(2);

    int ordinal;

    Frequency(int ord) {
        this.ordinal = ord;
    }

    public static Frequency byOrdinal(int ord) {
        return Frequency.values()[ord];
    }

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
