package com.mycompany.tokointest.models;

/**
 * Enumeration for holding user fields.
 *
 * @author Love
 */
public enum OrganizationField {

    ID(0),
    URL(1),
    EXTERNAL_ID(2),
    NAME(3),
    DOMAIN_NAMES(4),
    CREATED_AT(5),
    DETAILS(6),
    SHARED_TICKETS(7),
    TAGS(8);

    private OrganizationField (int value){
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
    public static OrganizationField findBy(int value) {
        for (OrganizationField rewardType : OrganizationField.values()) {
            if (value == rewardType.getValue()) {
                return rewardType;
            }
        }

        throw new IllegalArgumentException("Failed to find UserField by value.");
    }
}
