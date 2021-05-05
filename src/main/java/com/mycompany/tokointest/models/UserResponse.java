package com.mycompany.tokointest.models;

import java.util.List;

public class UserResponse {
	private int id;
	private List<String> assigneeTicketSubjects;
	private List<String> submittedTicketSubjects;
	private String organizationName;

	public UserResponse(int id, List<String> assigneeTicketSubjects, List<String> submittedTicketSubjects,
			String organizationName) {
		this.id = id;
		this.assigneeTicketSubjects = assigneeTicketSubjects;
		this.submittedTicketSubjects = submittedTicketSubjects;
		this.organizationName = organizationName;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", assigneeTicketSubjects=" + assigneeTicketSubjects
				+ ", submittedTicketSubjects=" + submittedTicketSubjects + ", organizationName=" + organizationName
				+ "]";
	}
}