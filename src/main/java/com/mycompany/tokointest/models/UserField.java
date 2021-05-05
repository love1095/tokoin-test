package com.mycompany.tokointest.models;

/**
 * Enumeration for holding user fields.
 *
 * @author Love
 */
public enum UserField {

	_ID,
    URL,
    EXTERNAL_ID,
    NAME,
    ALIAS,
    CREATED_AT,
    ACTIVE,
    VERIFIED,
    SHARED,
    LOCALE,
    TIMEZONE,
    LAST_LOGIN_AT,
    EMAIL,
    PHONE,
    SIGNATURE,
    ORGANIZATION_ID,
    TAGS,
    SUSPENDED,
    ROLE;

    /**
     * Find user field by the given value.
     *
     * @param value
     *		the value of enum
     * @return UserField if there is legal user field, otherwise throw exception
     */
    public static UserField findBy(String fieldName) {
        for (UserField rewardType : UserField.values()) {
            if (fieldName.equalsIgnoreCase(rewardType.name().toLowerCase())) {
                return rewardType;
            }
        }
        return null;
    }
}
