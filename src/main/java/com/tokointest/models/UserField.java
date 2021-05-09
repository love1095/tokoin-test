package com.tokointest.models;

/**
 * Enumeration for holding user fields.
 *
 * @author Love
 */
public enum UserField {

    ID("_id"),
    URL("url"),
    EXTERNAL_ID("external_id"),
    NAME("name"),
    ALIAS("alias"),
    CREATED_AT("created_at"),
    ACTIVE("active"),
    VERIFIED("verified"),
    SHARED("shared"),
    LOCALE("locale"),
    TIMEZONE("timezone"),
    LAST_LOGIN_AT("last_login_at"),
    EMAIL("email"),
    PHONE("phone"),
    SIGNATURE("signature"),
    ORGANIZATION_ID("organization_id"),
    TAGS("tags"),
    SUSPENDED("suspended"),
    ROLE("role");

    private UserField (String value){
        this.value = value;
    }

    private final String value;

    public String getValue() { return value; }

    /**
     * Find user field by the given value.
     *
     * @param value
     *		the value of enum
     * @return UserField if there is legal user field, otherwise throw exception
     */
    public static UserField findBy(String value) {
        for (UserField type : UserField.values()) {
            if (value.equalsIgnoreCase(type.getValue())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Failed to find UserField by value.");
    }
}
