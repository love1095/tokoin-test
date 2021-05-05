package com.mycompany.tokointest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Ticket extends BaseEntity{

	public static final String COLLECTION_NAME = "Tickets";

	private String type;

	private String subject;

	private String description;

	private String priority;

	private String status;

	@JsonProperty("submitter_id")
	private int submitterId;

	@JsonProperty("assignee_id")
	private int assigneeId;

	@JsonProperty("organization_id")
	private int organizationId;

	@JsonProperty("has_incidents")
	private boolean hasIncidents;

	@JsonProperty("due_at")
	private String dueAt;

	private String via;
}