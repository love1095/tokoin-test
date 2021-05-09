package com.tokointest.models;

/**
 * Enumeration for holding ticket fields.
 *
 * @author Love
 */
public enum TicketField {

    ID("_id"),
    URL("url"),
    EXTERNAL_ID("external_id"),
    CREATED_AT("created_at"),
    TYPE("type"),
    SUBJECT("subject"),
    DESCRIPTION("description"),
    PRIORITY("priority"),
    STATUS("status"),
    SUBMITTER_ID("submitter_id"),
    ASSIGNEE_ID("assignee_id"),
    ORGANIZATION_ID("organization_id"),
    TAGS("tags"),
    HAS_INCIDENTS("has_incidents"),
    DUE_AT("due_at"),
    VIA("via");

    private TicketField (String value){
        this.value = value;
    }

    private final String value;

    public String getValue() { return value; }

    /**
	 * Find fields by the given value.
	 *
	 * @param value the value of enum
	 * @return TicketField if there is legal field, otherwise throw exception
	 */
	public static TicketField findBy(String value) {
		for (TicketField type : TicketField.values()) {
			if (value.equalsIgnoreCase(type.getValue())) {
				return type;
			}
		}
		return null;
	}
}
