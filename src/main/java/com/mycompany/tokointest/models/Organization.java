package com.mycompany.tokointest.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Organization {
	@JsonProperty("_id")
	private int id;

	@JsonProperty("url")
	private String url;

	@JsonProperty("external_id")
	private String externalId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("domain_names")
	private List<String> domainNames;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("details")
	private String details;

	@JsonProperty("shared_tickets")
	private boolean sharedTickets;

	@JsonProperty("tags")
	private List<String> tags;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDomainNames() {
		return domainNames;
	}

	public void setDomainNames(List<String> domainNames) {
		this.domainNames = domainNames;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isSharedTickets() {
		return sharedTickets;
	}

	public void setSharedTickets(boolean sharedTickets) {
		this.sharedTickets = sharedTickets;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}