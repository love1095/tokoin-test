package com.tokointest.services.internal;

import static com.tokointest.models.OrganizationField.ORGANIZATION_NAME;
import static com.tokointest.models.TicketField.ID;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;
import com.tokointest.models.DataResponse;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
import com.tokointest.repository.OrganizationRepository;
import com.tokointest.repository.TicketRepository;
import com.tokointest.repository.UserRepository;
import com.tokointest.services.TicketsService;

import lombok.RequiredArgsConstructor;

/**
 * Search implement of {@link Ticket}.
 *
 * @author Love
 * @param <Ticket>
 *             type of object of ticket
 */
@Service @RequiredArgsConstructor
public class TokoinTicketsService extends AbstractService implements TicketsService {
	private final OrganizationRepository organizationMapper;
	private final TicketRepository ticketMapper;
	private final UserRepository userMapper;

	@Override
	public List<DataResponse<Ticket>> process(String term, String value) {
		List<Ticket> tickets = findData(ticketMapper.getEntityData(), Ticket.class, term, value);
		List<DataResponse<Ticket>> response = new LinkedList<>();
		if (!tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				JSONArray userArray = userMapper.getEntityData();
				List<User> assignees = findData(userArray, User.class, ID.getValue(),
						String.valueOf(ticket.getAssigneeId()));
				List<User> submiters = findData(userArray, User.class, ID.getValue(),
						String.valueOf(ticket.getSubmitterId()));
				List<Organization> organization = findData(organizationMapper.getEntityData(), Organization.class,
						OrganizationField.ID.getValue(), String.valueOf(ticket.getOrganizationId()));
				response.add(new DataResponse<Ticket>(ticket, getResponse(assignees, submiters, organization)));
			}
		}
		return response;
	}

	@VisibleForTesting
	public Map<String, String> getResponse(List<User> assignees, List<User> submiters, List<Organization> organization) {
		List<String> names = ListUtils.union(assignees, submiters).stream().map(User::getName)
				.collect(Collectors.toList());
		LinkedHashMap<String, String> nameMap = addItemInListToMap(names, UserField.NAME.getValue());
		List<String> organizationNames = organization.stream().map(Organization::getName).collect(Collectors.toList());
		LinkedHashMap<String, String> organizationNameMap = addItemInListToMap(organizationNames,
				ORGANIZATION_NAME.name().toLowerCase());
		LinkedHashMap<String, String> result = organizationNameMap;
		result.putAll(nameMap);
		return result;
	}

	@Override
	public List<String> getSearchableFields() {
		List<TicketField> items = Arrays.asList(getTicketFields());
		return items.stream().map(TicketField::getValue).collect(Collectors.toList());
	}

	@VisibleForTesting
	public TicketField[] getTicketFields() {
		return TicketField.values();
	}
}
