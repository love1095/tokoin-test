package com.mycompany.tokointest.models;

/**
 * Enumeration for holding user fields.
 *
 * @author Love
 */
public enum UserField {

    ID(0),
    URL(1),
    EXTERNAL_ID(2),
    NAME(3),
    ALIAS(4),
    CREATED_AT(5),
    ACTIVE(6),
    VERIFIED(7),
    SHARED(8),
    LOCALE(9),
    TIMEZONE(10),
    LAST_LOGIN_AT(11),
    EMAIL(12),
    PHONE(13),
    SIGNATURE(14),
    ORGANIZATION_ID(15),
    TAGS(16),
    SUSPENDED(17),
    ROLE(18);

    private UserField (int value){
        this.value = value;
    }

    private final int value;

    public int getValue() { return value; }

    /**
     * Find user field by the given value.
     *
     * @param value
     *		the value of enum
     * @return UserField if there is legal user field, otherwise throw exception
     */
    public static UserField findBy(int value) {
        for (UserField rewardType : UserField.values()) {
            if (value == rewardType.getValue()) {
                return rewardType;
            }
        }

        throw new IllegalArgumentException("Failed to find UserField by value.");
    }
}
