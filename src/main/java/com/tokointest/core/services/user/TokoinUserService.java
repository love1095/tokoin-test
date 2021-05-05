package com.tokointest.core.services.user;

import static com.tokointest.core.utils.TokoinJsonReader.findDataFromJson;
import static com.tokointest.core.utils.TokoinJsonReader.findIdDatasFromJson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;

import com.tokointest.core.repositories.organization.OrganizationMapper;
import com.tokointest.core.repositories.ticket.TicketMapper;
import com.tokointest.core.repositories.user.UserMapper;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.TicketField;
import com.tokointest.models.TokoinType;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
import com.tokointest.models.UserResponse;

/**
 * TokionService.
 *
 * @author Love
 *
 */
public class TokoinUserService implements UserService {
	private static OrganizationMapper organizationMapper;
	private static TicketMapper ticketMapper;
	private static UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<User> users = findDataFromJson(userMapper.getJsonArray(), User.class, field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!users.isEmpty()) {
			for (User user : users) {
				JSONArray ticketArray = ticketMapper.getJsonArray();
				String userId = user.getId();
				List<User> assignees = findDataFromJson(ticketArray, User.class,
						TicketField.ASSIGNEE_ID.name().toLowerCase(), userId);
				List<User> submiters = findDataFromJson(ticketArray, User.class,
						TicketField.SUBMITTER_ID.name().toLowerCase(), userId);
				List<Organization> organization = findIdDatasFromJson(organizationMapper.getJsonArray(),
						Organization.class, OrganizationField._ID.name().toLowerCase(), user.getOrganizationId());
				response.add(getTicketResponse(Integer.parseInt(userId), assignees, submiters, organization));
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

	@Override
	public List<String> getSearchableFields() {
		List<UserField> items = Arrays.asList(UserField.values());
		return items.stream().map(UserField::name).collect(Collectors.toList());
	}
}
