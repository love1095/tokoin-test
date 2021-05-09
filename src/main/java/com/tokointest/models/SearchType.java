package com.tokointest.models;

/**
 * Enumeration for holding search types.
 *
 * @author Love
 */
public enum SearchType {

    USERS("1"),
    TICKETS("2"),
    ORGANIZATION("3"),
    ALL("4"),
    QUIT("quit"),
    INVALID("invalid");

    private SearchType (String value){
        this.value = value;
    }

    private final String value;

    public String getValue() { return value; }

    /**
     * Find search type by the given value.
     *
     * @param value
     *		the value of enum
     * @return SearchType if there is legal search type, otherwise throw exception
     */
    public static SearchType findBy(String value) {
        for (SearchType type : SearchType.values()) {
            if (value.equalsIgnoreCase(type.getValue())) {
                return type;
            }
        }

        return INVALID;
    }
}
