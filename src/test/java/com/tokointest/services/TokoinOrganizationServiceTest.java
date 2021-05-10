package com.tokointest.services;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tokointest.models.DataResponse;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.models.User;
import com.tokointest.repository.OrganizationRepository;
import com.tokointest.repository.TicketRepository;
import com.tokointest.repository.UserRepository;
import com.tokointest.services.internal.TokoinOrganizationService;

import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for #{TokoinOrganizationService}.
 *
 * @author Love
 */

@RunWith(MockitoJUnitRunner.class)
public class TokoinOrganizationServiceTest {
	public static final String SEARCH_TERM = "name";
	public static final String SEARCH_VALUE = "Francisca";
	public static final String SUBJECT_1 = "sub 1";
	public static final String SUBJECT_2 = "sub 2";
	public static final String USER_NAME = "name 2";
	public static final String ORGANIZATION_NAME = "Francisca";
	public static final String URL = "url";
	public static final String DETAILS = "details";
	public static final int USER_ID = 10;
	public static final int ORGANIZATION_ID = 10;
	public static final String ORGANIZATION_ID_STRING = "10";
	public static final List<String> EMPTY_LIST = new LinkedList<>();
	public static final LinkedHashMap<String, String> EMPTY_MAP = new LinkedHashMap<String, String>();
	public static final String ASSIGNEE_ID_TERM = TicketField.ASSIGNEE_ID.getValue();
	public static final String SUBMITTER_ID_TERM = TicketField.SUBMITTER_ID.getValue();
	public static final String ORGANIZATION_ID_TERM = TicketField.ORGANIZATION_ID.getValue();
	public static final String SUBJECT_TERM = TicketField.SUBJECT.getValue();
	public static final String ORGANIZATION_NAME_TERM = OrganizationField.ORGANIZATION_NAME.name().toLowerCase();


	@InjectMocks @Spy
	private TokoinOrganizationService underTest;

	@Mock
	private OrganizationRepository organizationMapper;

	@Mock
	private TicketRepository ticketMapper;

	@Mock
	private UserRepository userMapper;

    @Test
    public void testUserSearchProcessShouldReturnCorrectDataWhenOrganizationsAreFound() {
        // given
        User user = mock(User.class);
        Ticket ticket = mock(Ticket.class);
        Organization organization = mock(Organization.class);
        List<User> users = Arrays.asList(user);
        List<Ticket> tickets = Arrays.asList(ticket);
        List<Organization> organizations = Arrays.asList(organization);
        Map<String, String> relatedEntities = new LinkedHashMap<>();
        JSONArray userArray = new JSONArray();
        JSONArray ticketArray = new JSONArray();
        JSONArray organizationArray = new JSONArray();
        when(userMapper.getEntityData()).thenReturn(userArray);
        when(ticketMapper.getEntityData()).thenReturn(ticketArray);
        when(organizationMapper.getEntityData()).thenReturn(organizationArray);
        when(organization.getId()).thenReturn(ORGANIZATION_ID);
        doReturn(organizations).when(underTest).findData(organizationArray,
                Organization.class, SEARCH_TERM, SEARCH_VALUE);
        doReturn(users).when(underTest).findData(userArray, User.class,
                ORGANIZATION_ID_TERM, ORGANIZATION_ID_STRING);
        doReturn(tickets).when(underTest).findData(ticketArray, Ticket.class,
                ORGANIZATION_ID_TERM, ORGANIZATION_ID_STRING);
        doReturn(relatedEntities).when(underTest).getResponse(tickets, users);

        // when
        List<DataResponse<Organization>> actual = underTest.process(SEARCH_TERM,
                SEARCH_VALUE);

        // then
        assertNotNull(actual);
        assertTrue(actual.size() == 1);
        assertSame(relatedEntities, actual.get(0).getRelatedEntities());
        verify(underTest).findData(organizationArray, Organization.class, SEARCH_TERM,
                SEARCH_VALUE);
        verify(underTest).findData(userArray, User.class, ORGANIZATION_ID_TERM,
                ORGANIZATION_ID_STRING);
        verify(underTest).findData(ticketArray, Ticket.class, ORGANIZATION_ID_TERM,
                ORGANIZATION_ID_STRING);
        verify(underTest).getResponse(tickets, users);
    }

    @Test
    public void testUserSearchProcessShouldReturnEmptyDataWhenOrganizationsAreNotFound() {
        // given
        List<Organization> organizations = new LinkedList<>();
        JSONArray array = new JSONArray();
        when(organizationMapper.getEntityData()).thenReturn(array);
        doReturn(organizations).when(underTest).findData(array, Organization.class,
                SEARCH_TERM, SEARCH_VALUE);

        // when
        List<DataResponse<Organization>> actual = underTest.process(SEARCH_TERM,
                SEARCH_VALUE);

        // then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(underTest, never()).getResponse(anyListOf(Ticket.class),
                anyListOf(User.class));
    }

    @Test
    public void testGetResponseShouldReturnCorrectDataWhenCalled() {
        // given
        Ticket ticket = mock(Ticket.class);
        User user = mock(User.class);
        List<String> subjects1 = Arrays.asList(SUBJECT_1);
        List<String> subjects2 = Arrays.asList(USER_NAME);
        List<Ticket> tickets = Arrays.asList(ticket);
        List<User> users = Arrays.asList(user);
        LinkedHashMap<String, String> expected = EMPTY_MAP;
        LinkedHashMap<String, String> expectedMap = EMPTY_MAP;
        LinkedHashMap<String, String> expectedMap1 = EMPTY_MAP;
        when(ticket.getSubject()).thenReturn(SUBJECT_1);
        when(user.getName()).thenReturn(USER_NAME);
        doReturn(expectedMap).when(underTest).addItemInListToMap(subjects1, SUBJECT_TERM);
        doReturn(expectedMap1).when(underTest).addItemInListToMap(subjects2,
                SUBJECT_TERM);

        // when
        Map<String, String> actual = underTest.getResponse(tickets, users);

        // then
        assertNotNull(actual);
        assertSame(expected, actual);
        verify(underTest).addItemInListToMap(subjects1, SUBJECT_TERM);
        verify(underTest).addItemInListToMap(subjects2, SUBJECT_TERM);
    }

    @Test
    public void testGetSearchableFieldsShouldReturnCorrectDataWhenCalled() {
        // given
        OrganizationField[] fields = new OrganizationField[2];
        fields[0] = OrganizationField.URL;
        fields[1] = OrganizationField.DETAILS;
        doReturn(fields).when(underTest).getOrganizationFields();

        // when
        List<String> actual = underTest.getSearchableFields();

        // then
        assertNotNull(actual);
        assertTrue(actual.size() == 2);
        assertSame(URL, actual.get(0));
        assertSame(DETAILS, actual.get(1));
        verify(underTest).getOrganizationFields();
    }
}
