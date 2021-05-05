package com.mycompany.tokointest.models;

public class TicketResponse {
	private String id;
	private String assigneeName;
	private String submitterName;
	private String organizationName;

	public TicketResponse(String id, String assigneeName, String submitterName, String organizationName) {
		this.id = id;
		this.assigneeName = assigneeName;
		this.submitterName = submitterName;
		this.organizationName = organizationName;
	}

	@Override
	public String toString() {
		return "TicketResponse [id=" + id + ", assigneeName=" + assigneeName + ", submitterName=" + submitterName
				+ ", organizationName=" + organizationName + "]";
	}
}