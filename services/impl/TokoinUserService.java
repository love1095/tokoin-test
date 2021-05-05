package com.mycompany.tokointest.services.impl;

import static com.mycompany.tokointest.config.TokoinConstants.ORGANIZATIONS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.TICKETS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.USER_JSON;
import static com.mycompany.tokointest.read.TokoinJsonReader.findDataFromJson;
import static com.mycompany.tokointest.read.TokoinJsonReader.findIdDatasFromJson;
import static com.mycompany.tokointest.read.TokoinJsonReader.findJsonArray;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;

import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.User;
import com.mycompany.tokointest.models.UserField;
import com.mycompany.tokointest.models.UserResponse;
import com.mycompany.tokointest.services.UserService;

/**
 * TokionService.
 *
 * @author Love
 *
 */
public class TokoinUserService implements UserService {

	public List<String> userSearchProcess(TokoinType type, UserField fields, String value) throws Exception {
		List<User> users = findDataFromJson(USER_JSON, User.class, fields.name().toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!users.isEmpty()) {
			for (User user : users) {
				int userId = user.getId();
				JSONArray userArray = findJsonArray(USER_JSON);
				List<User> assignees = findIdDatasFromJson(userArray, User.class,
						TicketField.ASSIGNEE_ID.name().toLowerCase(), userId);
				List<User> submiters = findIdDatasFromJson(userArray, User.class,
						TicketField.SUBMITTER_ID.name().toLowerCase(), userId);
				List<Organization> organization = findIdDatasFromJson(ORGANIZATIONS_JSON, Organization.class,
						OrganizationField._ID.name().toLowerCase(), user.getOrganizationId());
				response.add(getTicketResponse(userId, assignees, submiters, organization));
			}

		}
		return response;
	}

	String getTicketResponse(int id, List<User> assignees, List<User> submiters, List<Organization> organization) {
		List<String> assigneeNames = assignees.stream().map(User::getName).collect(Collectors.toList());
		List<String> submiterNames = submiters.stream().map(User::getName).collect(Collectors.toList());
		String organizationName = assignees.isEmpty() ? "" : organization.get(0).getName();
		return new UserResponse(id, assigneeNames, submiterNames, organizationName).toString();
	}
}
