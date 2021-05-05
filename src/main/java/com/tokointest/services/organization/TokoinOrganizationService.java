package com.tokointest.services.organization;

import static com.tokointest.utils.TokoinJsonReader.findDataFromJson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.OrganizationResponse;
import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.models.TokoinType;
import com.tokointest.models.User;
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
public class TokoinOrganizationService implements OrganizationService {
	private final OrganizationMapper organizationMapper;
	private final TicketMapper ticketMapper;
	private final UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<Organization> organizations = findDataFromJson(organizationMapper.getJsonArray(), Organization.class,
				field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!organizations.isEmpty()) {
			for (Organization organization : organizations) {
				String id = organization.getId();
				List<Ticket> tickets = findDataFromJson(ticketMapper.getJsonArray(), Ticket.class,
						TicketField.ORGANIZATION_ID.getValue(), id);
				List<User> user = findDataFromJson(userMapper.getJsonArray(), User.class,
						TicketField.ORGANIZATION_ID.getValue(), id);
				response.add(getOrganizationResponse(Integer.parseInt(id), tickets, user));
			}

		}
		return response;
	}

	String getOrganizationResponse(int id, List<Ticket> tickets, List<User> user) {
		List<String> subjects = tickets.stream().map(Ticket::getSubject).collect(Collectors.toList());
		List<String> userNames = user.stream().map(User::getName).collect(Collectors.toList());
		return new OrganizationResponse(id, subjects, userNames).toString();
	}

	@Override
	public List<String> getSearchableFields() {
		List<OrganizationField> items = Arrays.asList(OrganizationField.values());
		return items.stream().map(OrganizationField::getValue).collect(Collectors.toList());
	}
}
