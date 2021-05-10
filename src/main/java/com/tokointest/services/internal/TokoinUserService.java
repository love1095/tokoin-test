package com.tokointest.services.internal;

import static com.tokointest.models.OrganizationField.ORGANIZATION_NAME;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.tokointest.services.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Search implement of {@link User}.
 *
 * @author Love
 * @param <User>
 *             type of object of user
 */
@Service
@RequiredArgsConstructor
public class TokoinUserService extends AbstractService implements UserService {

    private final OrganizationRepository organizationMapper;
    private final TicketRepository ticketMapper;
    private final UserRepository userMapper;

    @Override
    public List<DataResponse<User>> process(String term, String value) {
        List<User> users = findData(userMapper.getEntityData(), User.class, term, value);
        List<DataResponse<User>> response = new LinkedList<>();
        if (!users.isEmpty()) {
            for (User user : users) {
                JSONArray ticketArray = ticketMapper.getEntityData();
                String userId = String.valueOf(user.getId());
                List<Ticket> assignees = findData(ticketArray, Ticket.class,
                        TicketField.ASSIGNEE_ID.getValue(), userId);
                List<Ticket> submiters = findData(ticketArray, Ticket.class,
                        TicketField.SUBMITTER_ID.getValue(), userId);
                List<Organization> organization = findData(
                        organizationMapper.getEntityData(), Organization.class,
                        OrganizationField.ID.getValue(),
                        String.valueOf(user.getOrganizationId()));
                response.add(new DataResponse<User>(user,
                        getResponse(assignees, submiters, organization)));
            }
        }
        return response;
    }

    @VisibleForTesting
    public Map<String, String> getResponse(List<Ticket> assignees, List<Ticket> submiters,
            List<Organization> organization) {
        List<String> subjects = ListUtils.union(assignees, submiters).stream()
                .map(Ticket::getSubject).collect(Collectors.toList());
        LinkedHashMap<String, String> submitedSubjects = addItemInListToMap(subjects,
                TicketField.SUBJECT.getValue());
        List<String> organizationName = organization.stream().map(Organization::getName)
                .collect(Collectors.toList());
        LinkedHashMap<String, String> organizationNames = addItemInListToMap(
                organizationName, ORGANIZATION_NAME.name().toLowerCase());
        LinkedHashMap<String, String> result = organizationNames;
        result.putAll(submitedSubjects);
        return result;
    }

    @Override
    public List<String> getSearchableFields() {
        List<UserField> items = Arrays.asList(getUserFields());
        return items.stream().map(UserField::getValue).collect(Collectors.toList());
    }

    @VisibleForTesting
    public UserField[] getUserFields() {
        return UserField.values();
    }

    @Override
    public boolean isCorrectFields(String term, String value) {
        boolean result = true;
        switch (UserField.findBy(term)) {
        case ID:
        case ORGANIZATION_ID:
            result = NumberUtils.isDigits(value) ? true : false;
            break;
        case VERIFIED:
        case ACTIVE:
        case SHARED:
        case SUSPENDED:
            result = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")
                    ? true : false;
            break;
        default:
            break;
        }
        return result;
    }
}
