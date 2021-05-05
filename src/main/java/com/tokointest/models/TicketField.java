package com.tokointest.models;

/**
 * Enumeration for holding ticket fields.
 *
 * @author Love
 */
public enum TicketField {

    _ID,
    URL,
    EXTERNAL_ID,
    CREATED_AT,
    TYPE,
    SUBJECT,
    DESCRIPTION,
    PRIORITY,
    STATUS,
    SUBMITTER_ID,
    ASSIGNEE_ID,
    ORGANIZATION_ID,
    TAGS,
    HAS_INCIDENTS,
    DUE_AT,
    VIA;

    /**
	 * Find fields by the given value.
	 *
	 * @param value the value of enum
	 * @return TicketField if there is legal field, otherwise throw exception
	 */
	public static TicketField findBy(String value) {
		for (TicketField rewardType : TicketField.values()) {
			if (value.equalsIgnoreCase(rewardType.name().toLowerCase())) {
				return rewardType;
			}
		}
		return null;
	}
}
