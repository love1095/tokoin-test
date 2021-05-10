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
import com.tokointest.models.UserField;
import com.tokointest.repository.OrganizationRepository;
import com.tokointest.repository.TicketRepository;
import com.tokointest.repository.UserRepository;
import com.tokointest.services.internal.TokoinUserService;

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
 * Unit test for #{TokoinUserService}.
 *
 * @author Love
 */

@RunWith(MockitoJUnitRunner.class)
public class TokoinUserServiceTest {
	public static final String SEARCH_TERM = "name";
	public static final String SEARCH_VALUE = "Francisca";
	public static final String SUBJECT_1 = "sub 1";
	public static final String SUBJECT_2 = "sub 2";
	public static final String ORGANIZATION_NAME = "Francisca";
	public static final String ACTIVE_TERM = "active";
	public static final String EMAIL_TERM = "email";
	public static final int USER_ID = 10;
	public static final int ORGANIZATION_ID = 20;
	public static final List<String> EMPTY_LIST = new LinkedList<>();
	public static final LinkedHashMap<String, String> EMPTY_MAP = new LinkedHashMap<String, String>();
	public static final String ASSIGNEE_ID_TERM = TicketField.ASSIGNEE_ID.getValue();
	public static final String SUBMITTER_ID_TERM = TicketField.SUBMITTER_ID.getValue();
	public static final String ORGANIZATION_ID_TERM = OrganizationField.ID.getValue();
	public static final String SUBJECT_TERM = TicketField.SUBJECT.getValue();
	public static final String ORGANIZATION_NAME_TERM = OrganizationField.ORGANIZATION_NAME.name().toLowerCase();


	@InjectMocks @Spy
	private TokoinUserService underTest;

	@Mock
	private OrganizationRepository organizationMapper;

	@Mock
	private TicketRepository ticketMapper;

	@Mock
	private UserRepository userMapper;

    @Test
    public void testUserSearchProcessShouldReturnCorrectDataWhenUsersAreFound() {
        // given
        String idString = "10";
        String organizationId = "20";
        User user = mock(User.class);
        Ticket ticket1 = mock(Ticket.class);
        Ticket ticket2 = mock(Ticket.class);
        Organization organization = mock(Organization.class);
        List<User> users = Arrays.asList(user);
        List<Ticket> ticket1s = Arrays.asList(ticket1);
        List<Ticket> ticket2s = Arrays.asList(ticket2);
        List<Organization> organizations = Arrays.asList(organization);
        Map<String, String> relatedEntities = new LinkedHashMap<>();
        JSONArray userArray = new JSONArray();
        JSONArray ticketArray = new JSONArray();
        JSONArray organizationArray = new JSONArray();

        doReturn(users).when(underTest).findData(userArray, User.class, SEARCH_TERM,
                SEARCH_VALUE);
        when(userMapper.getEntityData()).thenReturn(userArray);
        when(ticketMapper.getEntityData()).thenReturn(ticketArray);
        when(organizationMapper.getEntityData()).thenReturn(organizationArray);
        when(user.getId()).thenReturn(USER_ID);
        when(user.getOrganizationId()).thenReturn(ORGANIZATION_ID);
        doReturn(ticket1s).when(underTest).findData(ticketArray, Ticket.class,
                ASSIGNEE_ID_TERM, idString);
        doReturn(ticket2s).when(underTest).findData(ticketArray, Ticket.class,
                SUBMITTER_ID_TERM, idString);
        doReturn(organizations).when(underTest).findData(organizationArray,
                Organization.class, ORGANIZATION_ID_TERM, organizationId);
        doReturn(relatedEntities).when(underTest).getResponse(ticket1s, ticket2s,
                organizations);

        // when
        List<DataResponse<User>> actual = underTest.process(SEARCH_TERM, SEARCH_VALUE);

        // then
        assertNotNull(actual);
        assertTrue(actual.size() == 1);
        assertSame(relatedEntities, actual.get(0).getRelatedEntities());
        verify(underTest).findData(userArray, User.class, SEARCH_TERM, SEARCH_VALUE);
        verify(underTest).findData(ticketArray, Ticket.class, ASSIGNEE_ID_TERM, idString);
        verify(underTest).findData(ticketArray, Ticket.class, SUBMITTER_ID_TERM,
                idString);
        verify(underTest).findData(organizationArray, Organization.class,
                ORGANIZATION_ID_TERM, organizationId);
        verify(underTest).getResponse(ticket1s, ticket2s, organizations);
    }

    @Test
    public void testUserSearchProcessShouldReturnEmptyDataWhenUsersAreNotFound() {
        // given
        List<User> users = Arrays.asList();
        JSONArray userArray = new JSONArray();
        when(userMapper.getEntityData()).thenReturn(userArray);
        doReturn(users).when(underTest).findData(userArray, User.class, SEARCH_TERM,
                SEARCH_VALUE);

        // when
        List<DataResponse<User>> actual = underTest.process(SEARCH_TERM, SEARCH_VALUE);

        // then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(underTest, never()).getResponse(anyListOf(Ticket.class),
                anyListOf(Ticket.class), anyListOf(Organization.class));
    }

    @Test
    public void testGetResponseShouldReturnCorrectDataWhenCalled() {
        // given
        Ticket ticket1 = mock(Ticket.class);
        Ticket ticket2 = mock(Ticket.class);
        Organization organization = mock(Organization.class);
        List<String> subjects = Arrays.asList(SUBJECT_1, SUBJECT_2);
        List<String> organizationNames = Arrays.asList(ORGANIZATION_NAME);
        List<Ticket> ticket1s = Arrays.asList(ticket1);
        List<Ticket> ticket2s = Arrays.asList(ticket2);
        List<Organization> organizations = Arrays.asList(organization);
        LinkedHashMap<String, String> expected = EMPTY_MAP;
        LinkedHashMap<String, String> expectedMap = EMPTY_MAP;
        LinkedHashMap<String, String> expectedOrganizationNameMap = EMPTY_MAP;
        when(ticket1.getSubject()).thenReturn(SUBJECT_1);
        when(ticket2.getSubject()).thenReturn(SUBJECT_2);
        when(organization.getName()).thenReturn(ORGANIZATION_NAME);
        doReturn(expectedMap).when(underTest).addItemInListToMap(subjects, SUBJECT_TERM);
        doReturn(expectedOrganizationNameMap).when(underTest)
                .addItemInListToMap(organizationNames, ORGANIZATION_NAME_TERM);

        // when
        Map<String, String> actual = underTest.getResponse(ticket1s, ticket2s,
                organizations);

        // then
        assertNotNull(actual);
        assertSame(expected, actual);
        verify(underTest).addItemInListToMap(subjects, SUBJECT_TERM);
        verify(underTest).addItemInListToMap(organizationNames, ORGANIZATION_NAME_TERM);
    }

    @Test
    public void testGetSearchableFieldsShouldReturnCorrectDataWhenCalled() {
        // given
        UserField[] fields = new UserField[2];
        fields[0] = UserField.ACTIVE;
        fields[1] = UserField.EMAIL;
        doReturn(fields).when(underTest).getUserFields();

        // when
        List<String> actual = underTest.getSearchableFields();

        // then
        assertNotNull(actual);
        assertTrue(actual.size() == 2);
        assertSame(ACTIVE_TERM, actual.get(0));
        assertSame(EMAIL_TERM, actual.get(1));
        verify(underTest).getUserFields();
    }
}
