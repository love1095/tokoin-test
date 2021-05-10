package com.tokointest.services.internal;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.annotations.VisibleForTesting;
import com.tokointest.models.DataResponse;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.Ticket;
import com.tokointest.models.User;
import com.tokointest.repository.OrganizationRepository;
import com.tokointest.repository.TicketRepository;
import com.tokointest.repository.UserRepository;
import com.tokointest.services.OrganizationService;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import static com.tokointest.models.TicketField.ORGANIZATION_ID;
import static com.tokointest.models.TicketField.SUBJECT;

/**
 * Search implement of {@link OrganizationService}.
 *
 * @author Love
 * @param <Organization>
 *             type of object of organization
 */
@Service @RequiredArgsConstructor
public class TokoinOrganizationService extends AbstractService implements OrganizationService {
	private final OrganizationRepository organizationMapper;
	private final TicketRepository ticketMapper;
	private final UserRepository userMapper;

    @Override
    public List<DataResponse<Organization>> process(String term, String value) {
        List<Organization> organizations = findData(organizationMapper.getEntityData(),
                Organization.class, term, value);
        List<DataResponse<Organization>> response = new LinkedList<>();
        if (!organizations.isEmpty()) {
            for (Organization organization : organizations) {
                int id = organization.getId();
                List<Ticket> tickets = findData(ticketMapper.getEntityData(),
                        Ticket.class, ORGANIZATION_ID.getValue(), String.valueOf(id));
                List<User> user = findData(userMapper.getEntityData(), User.class,
                        ORGANIZATION_ID.getValue(), String.valueOf(id));
                response.add(new DataResponse<Organization>(organization,
                        getResponse(tickets, user)));
            }
        }
        return response;
    }

    @VisibleForTesting
    public Map<String, String> getResponse(List<Ticket> tickets, List<User> users) {
        List<String> subjects = tickets.stream().map(Ticket::getSubject)
                .collect(Collectors.toList());
        LinkedHashMap<String, String> subjectMap = addItemInListToMap(subjects,
                SUBJECT.getValue());
        List<String> userNames = users.stream().map(User::getName)
                .collect(Collectors.toList());
        LinkedHashMap<String, String> userNameMap = addItemInListToMap(userNames,
                SUBJECT.getValue());
        LinkedHashMap<String, String> result = userNameMap;
        result.putAll(subjectMap);
        return result;
    }

    @Override
    public List<String> getSearchableFields() {
        List<OrganizationField> items = Arrays.asList(getOrganizationFields());
        return items.stream().map(OrganizationField::getValue)
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    public OrganizationField[] getOrganizationFields() {
        return OrganizationField.values();
    }

    @Override
    public boolean isCorrectFields(String term, String value) {
        boolean result = true;
        switch (OrganizationField.findBy(term)) {
        case ID:
            result = NumberUtils.isDigits(value) ? true : false;
            break;
        case SHARED_TICKETS:
            result = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")
                    ? true : false;
            break;
        default:
            break;
        }
        return result;
    }
}
