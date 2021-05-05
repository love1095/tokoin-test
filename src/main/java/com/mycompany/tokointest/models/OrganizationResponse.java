package com.mycompany.tokointest.models;

import java.util.List;

public class OrganizationResponse {
	private int id;
	private List<String> ticketSubjects;
	private List<String> userName;

	public OrganizationResponse(int id, List<String> ticketSubjects, List<String> userName) {
		this.id = id;
		this.ticketSubjects = ticketSubjects;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "OrganizationResponse [id=" + id + ", ticketSubjects=" + ticketSubjects + ", userName=" + userName + "]";
	}
	
}