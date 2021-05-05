package com.tokointest.services.ticket;

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
import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.models.TicketResponse;
import com.tokointest.models.TokoinType;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
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
public class TokoinTicketsService implements TicketsService {
	private final OrganizationMapper organizationMapper;
	private final TicketMapper ticketMapper;
	private final UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<Ticket> tickets = findDataFromJson(ticketMapper.getJsonArray(), Ticket.class, field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				JSONArray userArray = userMapper.getJsonArray();
				List<User> assignees = findIdDatasFromJson(userArray, User.class, UserField.ID.getValue(),
						ticket.getAssigneeId());
				List<User> submiter = findIdDatasFromJson(userArray, User.class, UserField.ID.getValue(),
						ticket.getSubmitterId());
				List<Organization> organization = findIdDatasFromJson(organizationMapper.getJsonArray(),
						Organization.class, OrganizationField.ID.getValue(), ticket.getOrganizationId());
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

	@Override
	public List<String> getSearchableFields() {
		List<TicketField> items = Arrays.asList(TicketField.values());
		return items.stream().map(TicketField::getValue).collect(Collectors.toList());
	}
}
