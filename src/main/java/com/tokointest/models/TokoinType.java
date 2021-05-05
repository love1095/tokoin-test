package com.tokointest.models;

/**
 * Enumeration for holding tokoin types.
 *
 * @author Love
 */
public enum TokoinType {

    USERS(0),
    TICKETS(1),
    ORGANIZATION(2);

    private TokoinType (int value){
        this.value = value;
    }

    private final int value;

    public int getValue() { return value; }

    /**
     * Find tokoin type by the given value.
     *
     * @param value
     *		the value of enum
     * @return TokoinType if there is legal tokoin type, otherwise throw exception
     */
    public static TokoinType findBy(int value) {
        for (TokoinType rewardType : TokoinType.values()) {
            if (value == rewardType.getValue()) {
                return rewardType;
            }
        }

        throw new IllegalArgumentException("Failed to find TokoinType by value.");
    }
}
