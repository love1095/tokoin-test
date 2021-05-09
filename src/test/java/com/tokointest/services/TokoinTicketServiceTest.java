package com.tokointest.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

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
import com.tokointest.services.internal.TokoinTicketsService;

/**
 * Unit test for #{TokoinTicketsService}.
 *
 * @author Love
 */

@RunWith(MockitoJUnitRunner.class)
public class TokoinTicketServiceTest {
	public static final String SEARCH_TERM = "name";
	public static final String SEARCH_VALUE = "Francisca";
	public static final String NAME_1 = "name 1";
	public static final String NAME_2 = "name 2";
	public static final String ORGANIZATION_NAME = "Francisca";
	public static final String DESCRIPTION_TERM = "description";
	public static final String URL_TERM = "url";
	public static final int ASSIGNEE_ID = 10;
	public static final int SUBMITED_ID = 40;
	public static final int ORGANIZATION_ID = 20;
	public static final List<String> EMPTY_LIST = new LinkedList<>();
	public static final LinkedHashMap<String, String> EMPTY_MAP = new LinkedHashMap<String, String>();
	public static final String ID_TERM = TicketField.ID.getValue();
	public static final String SUBMITTER_ID_TERM = TicketField.SUBMITTER_ID.getValue();
	public static final String ORGANIZATION_ID_TERM = OrganizationField.ID.getValue();
	public static final String NAME_TERM = UserField.NAME.getValue();
	public static final String ORGANIZATION_NAME_TERM = OrganizationField.ORGANIZATION_NAME.name().toLowerCase();

	@InjectMocks @Spy
	private TokoinTicketsService underTest;

	@Mock
	private OrganizationRepository organizationMapper;

	@Mock
	private TicketRepository ticketMapper;

	@Mock
	private UserRepository userMapper;
	
    @Test
    public void testProcessShouldReturnCorrectDataWhenTicketsAreFound() {
    	// given
    	String organizationId = "20";
    	Ticket ticket = mock(Ticket.class);
    	User user1 = mock(User.class);
    	User user2 = mock(User.class);
    	Organization organization = mock(Organization.class);
    	List<Ticket> tickets = List.of(ticket);
    	List<User> users1 = List.of(user1);
    	List<User> users2 = List.of(user2);
    	List<Organization> organizations = List.of(organization);
    	Map<String, String> relatedEntities = new LinkedHashMap<>();
    	JSONArray userArray = new JSONArray();
    	JSONArray ticketArray = new JSONArray();
    	JSONArray organizationArray = new JSONArray();
    	
        doReturn(tickets).when(underTest).findData(ticketArray, Ticket.class, SEARCH_TERM, SEARCH_VALUE);
    	when(userMapper.getEntityData()).thenReturn(userArray);
    	when(ticketMapper.getEntityData()).thenReturn(ticketArray);
    	when(organizationMapper.getEntityData()).thenReturn(organizationArray);
    	when(ticket.getAssigneeId()).thenReturn(ASSIGNEE_ID);
    	when(ticket.getSubmitterId()).thenReturn(SUBMITED_ID);
    	when(ticket.getOrganizationId()).thenReturn(ORGANIZATION_ID);
        doReturn(users1).when(underTest).findData(userArray, User.class, ID_TERM, String.valueOf(ASSIGNEE_ID));
		doReturn(users2).when(underTest).findData(userArray, User.class, ID_TERM, String.valueOf(SUBMITED_ID));
		doReturn(organizations).when(underTest).findData(organizationArray, Organization.class, ORGANIZATION_ID_TERM,
				organizationId);
		doReturn(relatedEntities).when(underTest).getResponse(users1, users2, organizations);

		// when
        List<DataResponse<Ticket>> actual = underTest.process(SEARCH_TERM, SEARCH_VALUE);

        // then
        assertNotNull(actual);
        assertTrue(actual.size() == 1);
        assertSame(relatedEntities, actual.get(0).getRelatedEntities());
        verify(underTest).findData(ticketArray, Ticket.class, SEARCH_TERM, SEARCH_VALUE);
		verify(underTest).findData(userArray, User.class, ID_TERM, String.valueOf(ASSIGNEE_ID));
		verify(underTest).findData(userArray, User.class, ID_TERM, String.valueOf(SUBMITED_ID));
		verify(underTest).findData(organizationArray, Organization.class, ORGANIZATION_ID_TERM,
				organizationId);
        verify(underTest).getResponse(users1, users2, organizations);
    }
	
	@Test
	public void testhProcessShouldReturnEmptyDataWhenTicketsAreNotFound() {
		// given
		List<Ticket> tickets = List.of();
		JSONArray userArray = new JSONArray();
		when(ticketMapper.getEntityData()).thenReturn(userArray);
		doReturn(tickets).when(underTest).findData(userArray, Ticket.class, SEARCH_TERM, SEARCH_VALUE);

		// when
		List<DataResponse<Ticket>> actual = underTest.process(SEARCH_TERM, SEARCH_VALUE);

		// then
		assertNotNull(actual);
		assertTrue(actual.isEmpty());
		verify(underTest, never()).getResponse(anyListOf(User.class), anyListOf(User.class),
				anyListOf(Organization.class));
	}
	
	@Test
	public void testGetResponseShouldReturnCorrectDataWhenCalled() {
		// given
		User user1 = mock(User.class);
		User user2 = mock(User.class);
    	Organization organization = mock(Organization.class);
    	List<String> subjects = List.of(NAME_1, NAME_2);
    	List<String> organizationNames = List.of(ORGANIZATION_NAME);
    	List<User> user1s = List.of(user1);
    	List<User> user2s = List.of(user2);
		List<Organization> organizations = List.of(organization);
		LinkedHashMap<String, String> expected = EMPTY_MAP;
		LinkedHashMap<String, String> expectedMap = EMPTY_MAP;
		LinkedHashMap<String, String> expectedOrganizationNameMap = EMPTY_MAP;
		when(user1.getName()).thenReturn(NAME_1);
		when(user2.getName()).thenReturn(NAME_2);
		when(organization.getName()).thenReturn(ORGANIZATION_NAME);
		doReturn(expectedMap).when(underTest).addItemInListToMap(subjects, NAME_TERM);
		doReturn(expectedOrganizationNameMap).when(underTest).addItemInListToMap(organizationNames, ORGANIZATION_NAME_TERM);

		// when
		Map<String, String> actual = underTest.getResponse(user1s, user2s, organizations);

		// then
		assertNotNull(actual);
		assertSame(expected, actual);
		verify(underTest).addItemInListToMap(subjects, NAME_TERM);
		verify(underTest).addItemInListToMap(organizationNames, ORGANIZATION_NAME_TERM);
	}
	
	@Test
	public void testGetSearchableFieldsShouldReturnCorrectDataWhenCalled() {
		// given
		TicketField[ ] fields = new TicketField[2];
		fields[0] = TicketField.DESCRIPTION;
		fields[1] = TicketField.URL;
		doReturn(fields).when(underTest).getTicketFields();

		// when
		List<String> actual = underTest.getSearchableFields();

		// then
		assertNotNull(actual);
		assertTrue(actual.size() == 2);
		assertSame(DESCRIPTION_TERM, actual.get(0));
		assertSame(URL_TERM, actual.get(1));
		verify(underTest).getTicketFields();
	}
}
