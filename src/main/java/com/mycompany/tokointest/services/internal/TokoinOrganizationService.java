package com.mycompany.tokointest.services.internal;

import static com.mycompany.tokointest.reader.TokoinJsonReader.findDataFromJson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.tokointest.mapper.OrganizationMapper;
import com.mycompany.tokointest.mapper.TicketMapper;
import com.mycompany.tokointest.mapper.UserMapper;
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
	private static OrganizationMapper organizationMapper;
	private static TicketMapper ticketMapper;
	private static UserMapper userMapper;

	public List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception {
		List<Organization> organizations = findDataFromJson(organizationMapper.getJsonArray(), Organization.class,
				field.toLowerCase(), value);
		List<String> response = new LinkedList<>();
		if (!organizations.isEmpty()) {
			for (Organization organization : organizations) {
				String id = organization.getId();
				List<Ticket> tickets = findDataFromJson(ticketMapper.getJsonArray(), Ticket.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), id);
				List<User> user = findDataFromJson(userMapper.getJsonArray(), User.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), id);
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
		return items.stream().map(OrganizationField::name).collect(Collectors.toList());
	}
}
