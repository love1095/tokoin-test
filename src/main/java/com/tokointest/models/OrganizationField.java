package com.tokointest.models;

/**
 * Enumeration for holding user fields.
 *
 * @author Love
 */
public enum OrganizationField {

	_ID,
    URL,
    EXTERNAL_ID,
    NAME,
    DOMAIN_NAMES,
    CREATED_AT,
    DETAILS,
    SHARED_TICKETS,
    TAGS;

    /**
     * Find user field by the given value.
     *
     * @param value
     *		the value of enum
     * @return UserField if there is legal user field, otherwise throw exception
     */
    public static OrganizationField findBy(String value) {
        for (OrganizationField rewardType : OrganizationField.values()) {
            if (value.equalsIgnoreCase(rewardType.name().toLowerCase())) {
                return rewardType;
            }
        }

        throw new IllegalArgumentException("Failed to find UserField by value.");
    }
}
