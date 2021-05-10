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
import com.tokointest.models.TicketField;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
import com.tokointest.repository.OrganizationRepository;
import com.tokointest.repository.TicketRepository;
import com.tokointest.repository.UserRepository;
import com.tokointest.services.TicketsService;

import lombok.RequiredArgsConstructor;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import static com.tokointest.models.OrganizationField.ORGANIZATION_NAME;
import static com.tokointest.models.TicketField.ID;

/**
 * Search implement of {@link Ticket}.
 *
 * @author Love
 * @param <Ticket>
 *             type of object of ticket
 */
@Service
@RequiredArgsConstructor
public class TokoinTicketsService extends AbstractService implements TicketsService {

    private final OrganizationRepository organizationRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public List<DataResponse<Ticket>> process(String term, String value) {
        List<Ticket> tickets = findData(ticketRepository.getEntityData(), Ticket.class, term,
                value);
        List<DataResponse<Ticket>> response = new LinkedList<>();
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                JSONArray userArray = userRepository.getEntityData();
                List<User> assignees = findData(userArray, User.class, ID.getValue(),
                        String.valueOf(ticket.getAssigneeId()));
                List<User> submiters = findData(userArray, User.class, ID.getValue(),
                        String.valueOf(ticket.getSubmitterId()));
                List<Organization> organization = findData(
                        organizationRepository.getEntityData(), Organization.class,
                        OrganizationField.ID.getValue(),
                        String.valueOf(ticket.getOrganizationId()));
                response.add(new DataResponse<Ticket>(ticket,
                        getResponse(assignees, submiters, organization)));
            }
        }
        return response;
    }

    @VisibleForTesting
    public Map<String, String> getResponse(List<User> assignees, List<User> submiters,
            List<Organization> organization) {
        List<String> names = ListUtils.union(assignees, submiters).stream()
                .map(User::getName).collect(Collectors.toList());
        LinkedHashMap<String, String> nameMap = addItemInListToMap(names,
                UserField.NAME.getValue());
        List<String> organizationNames = organization.stream().map(Organization::getName)
                .collect(Collectors.toList());
        LinkedHashMap<String, String> organizationNameMap = addItemInListToMap(
                organizationNames, ORGANIZATION_NAME.name().toLowerCase());
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

    @Override
    public boolean isCorrectFields(String term, String value) {
        boolean result = true;
        switch (TicketField.findBy(term)) {
        case SUBMITTER_ID:
        case ASSIGNEE_ID:
        case ORGANIZATION_ID:
            result = NumberUtils.isDigits(value) ? true : false;
            break;
        case HAS_INCIDENTS:
            result = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")
                    ? true : false;
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public void initDatas() {
        organizationRepository.init();
        ticketRepository.init();
        userRepository.init();
    }
}
