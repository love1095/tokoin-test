package com.mycompany.tokointest.models;

/**
 * Enumeration for holding ticket fields.
 *
 * @author Love
 */
public enum TicketField {

    ID(0),
    URL(1),
    EXTERNAL_ID(2),
    CREATED_AT(3),
    TYPE(4),
    SUBJECT(5),
    DESCRIPTION(6),
    PRIORITY(7),
    STATUS(8),
    SUBMITTER_ID(9),
    ASSIGNEE_ID(10),
    ORGANIZATION_ID(11),
    TAGS(12),
    HAS_INCIDENTS(13),
    DUE_AT(14),
    VIA(15);

    private TicketField (int value){
        this.value = value;
    }

    private final int value;

    public int getValue() { return value; }

    /**
     * Find fields by the given value.
     *
     * @param value
     *		the value of enum
     * @return TicketField if there is legal field, otherwise throw exception
     */
    public static TicketField findBy(int value) {
        for (TicketField rewardType : TicketField.values()) {
            if (value == rewardType.getValue()) {
                return rewardType;
            }
        }

        throw new IllegalArgumentException("Failed to find TicketField by value.");
    }
}
