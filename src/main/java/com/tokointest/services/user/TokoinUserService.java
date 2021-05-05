package com.tokointest.services.user;

import static com.tokointest.utils.TokoinJsonReader.findDataFromJson;
import static com.tokointest.utils.TokoinJsonReader.findIdDatasFromJson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.TicketField;
import com.tokointest.models.TokoinType;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
import com.tokointest.models.UserResponse;
import com.tokointest.repositories.organization.OrganizationMapper;
import com.tokointest.repositories.ticket.TicketMapper;
import com.tokointest.repositories.user.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
@RequiredArgsConstructor
public class TokoinUserService implements UserService {
	private final OrganizationMapper organizationMapper;
	private final TicketMapper ticketMapper;
	private final UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<User> users = findDataFromJson(userMapper.getJsonArray(), User.class, field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!users.isEmpty()) {
			for (User user : users) {
				JSONArray ticketArray = ticketMapper.getJsonArray();
				String userId = user.getId();
				List<User> assignees = findDataFromJson(ticketArray, User.class, TicketField.ASSIGNEE_ID.getValue(),
						userId);
				List<User> submiters = findDataFromJson(ticketArray, User.class, TicketField.SUBMITTER_ID.getValue(),
						userId);
				List<Organization> organization = findIdDatasFromJson(organizationMapper.getJsonArray(),
						Organization.class, OrganizationField.ID.getValue(), user.getOrganizationId());
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
		return items.stream().map(UserField::getValue).collect(Collectors.toList());
	}
}
