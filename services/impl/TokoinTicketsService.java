package com.mycompany.tokointest.services.impl;

import static com.mycompany.tokointest.config.TokoinConstants.ORGANIZATIONS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.TICKETS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.USER_JSON;
import static com.mycompany.tokointest.read.TokoinJsonReader.findDataFromJson;
import static com.mycompany.tokointest.read.TokoinJsonReader.findIdDatasFromJson;
import static com.mycompany.tokointest.read.TokoinJsonReader.findJsonArray;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.models.Ticket;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TicketResponse;
import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.User;
import com.mycompany.tokointest.models.UserField;
import com.mycompany.tokointest.services.TicketsService;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
public class TokoinTicketsService implements TicketsService {

	public List<String> userSearchProcess(TokoinType type, TicketField field, String value) throws Exception {
		List<Ticket> tickets = findDataFromJson(TICKETS_JSON, Ticket.class, field.name().toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				JSONArray userArray = findJsonArray(USER_JSON);
				List<User> assignees = findIdDatasFromJson(userArray, User.class, UserField._ID.name().toLowerCase(),
						ticket.getAssigneeId());
				List<User> submiter = findIdDatasFromJson(userArray, User.class, UserField._ID.name().toLowerCase(),
						ticket.getSubmitterId());
				List<Organization> organization = findIdDatasFromJson(ORGANIZATIONS_JSON, Organization.class,
						OrganizationField._ID.name().toLowerCase(), ticket.getOrganizationId());
				response.add(getTicketResponse(ticket.getId(), assignees, submiter, organization));
			}
		}
		return response;
	}

	String getTicketResponse(String id, List<User> assignees, List<User> submiter,
			List<Organization> organization) {
		String assigneeName = assignees.isEmpty() ? "" : assignees.get(0).getName();
		String submiterName = submiter.isEmpty() ? "" : submiter.get(0).getName();
		String organizationName = organization.isEmpty() ? "" : organization.get(0).getName();
		return new TicketResponse(id, assigneeName, submiterName, organizationName).toString();
	}

}
