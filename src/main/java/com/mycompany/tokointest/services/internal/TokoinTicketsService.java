package com.mycompany.tokointest.services.internal;

import static com.mycompany.tokointest.config.TokoinConstants.ORGANIZATIONS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.TICKETS_JSON;
import static com.mycompany.tokointest.config.TokoinConstants.USER_JSON;
import static com.mycompany.tokointest.reader.TokoinJsonReader.findDataFromJson;
import static com.mycompany.tokointest.reader.TokoinJsonReader.findIdDatasFromJson;
import static com.mycompany.tokointest.reader.TokoinJsonReader.findJsonArray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.mycompany.tokointest.mapper.OrganizationMapper;
import com.mycompany.tokointest.mapper.TicketMapper;
import com.mycompany.tokointest.mapper.UserMapper;
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
	private static OrganizationMapper organizationMapper;
	private static TicketMapper ticketMapper;
	private static UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<Ticket> tickets = findDataFromJson(ticketMapper.getJsonArray(), Ticket.class, field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				JSONArray userArray = userMapper.getJsonArray();
				List<User> assignees = findIdDatasFromJson(userArray, User.class, UserField._ID.name().toLowerCase(),
						ticket.getAssigneeId());
				List<User> submiter = findIdDatasFromJson(userArray, User.class, UserField._ID.name().toLowerCase(),
						ticket.getSubmitterId());
				List<Organization> organization = findIdDatasFromJson(organizationMapper.getJsonArray(), Organization.class,
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

	@Override
	public List<String> getSearchableFields() {
		List<TicketField> items = Arrays.asList(TicketField.values());
		return items.stream().map(TicketField::name).collect(Collectors.toList());
	}
}
