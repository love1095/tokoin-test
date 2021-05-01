package com.mycompany.tokointest.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

	@JsonProperty("_id")
	private String id;

	@JsonProperty("url")
	private String url;

	@JsonProperty("external_id")
	private String externalId;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("description")
	private String description;

	@JsonProperty("priority")
	private String priority;

	@JsonProperty("status")
	private String status;

	@JsonProperty("submitter_id")
	private int submitterId;

	@JsonProperty("assignee_id")
	private int assigneeId;

	@JsonProperty("organization_id")
	private int organizationId;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("has_incidents")
	private boolean hasIncidents;

	@JsonProperty("due_at")
	private String dueAt;

	@JsonProperty("via")
	private String via;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(int submitterId) {
		this.submitterId = submitterId;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isHasIncidents() {
		return hasIncidents;
	}

	public void setHasIncidents(boolean hasIncidents) {
		this.hasIncidents = hasIncidents;
	}

	public String getDueAt() {
		return dueAt;
	}

	public void setDueAt(String dueAt) {
		this.dueAt = dueAt;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
}