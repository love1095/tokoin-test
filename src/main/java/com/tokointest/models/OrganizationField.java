package com.tokointest.models;

/**
 * Enumeration for holding organization fields.
 *
 * @author Love
 */
public enum OrganizationField {

	ID("_id"),
    URL("url"),
    EXTERNAL_ID("external_id"),
    ORGANIZATION_NAME("name"),
    DOMAIN_NAMES("domain_names"),
    CREATED_AT("created_at"),
    DETAILS("details"),
    SHARED_TICKETS("shared_tickets"),
    TAGS("tags");

	private OrganizationField (String value) {
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
    public static OrganizationField findBy(String value) {
        for (OrganizationField type : OrganizationField.values()) {
            if (value.equalsIgnoreCase(type.name())) {
                return type;
            }
        }

        return null;
    }
}
