package com.mycompany.tokointest.services.impl;

import static com.mycompany.tokointest.config.TokoinConstants.ORGANIZATIONS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.TICKETS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.USER_JSON;
import static com.mycompany.tokointest.read.TokoinJsonReader.findDataFromJson;
import static com.mycompany.tokointest.read.TokoinJsonReader.findIdDatasFromJson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.models.OrganizationResponse;
import com.mycompany.tokointest.models.Ticket;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.User;
import com.mycompany.tokointest.services.OrganizationService;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
public class TokoinOrganizationService implements OrganizationService {

	public List<String> userSearchProcess(TokoinType type, OrganizationField field, String value) throws Exception {
		List<Organization> organizations = findDataFromJson(ORGANIZATIONS_JSON, Organization.class,
				field.name().toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!organizations.isEmpty()) {
			for (Organization organization : organizations) {
				int id = organization.getId();
				List<Ticket> tickets = findIdDatasFromJson(TICKETS_JSON, Ticket.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), id);
				List<User> user = findIdDatasFromJson(USER_JSON, User.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), id);
				response.add(getOrganizationResponse(id, tickets, user));
			}

		}
		return response;
	}

	String getOrganizationResponse(int id, List<Ticket> tickets, List<User> user) {
		List<String> subjects = tickets.stream().map(Ticket::getSubject).collect(Collectors.toList());
		List<String> userNames = user.stream().map(User::getName).collect(Collectors.toList());
		return new OrganizationResponse(id, subjects, userNames).toString();
	}
}
