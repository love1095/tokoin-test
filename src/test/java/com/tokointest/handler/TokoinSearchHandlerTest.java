package com.tokointest.handler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.tokointest.handler.search.SearchOrganizationHandler;
import com.tokointest.handler.search.SearchTicketHandler;
import com.tokointest.handler.search.SearchUserHandler;
import com.tokointest.models.DataResponse;
import com.tokointest.models.SearchType;
import com.tokointest.models.Ticket;

/**
 * Unit test for #{TokoinSearchHandler}.
 *
 * @author Love
 */

@RunWith(MockitoJUnitRunner.class)
public class TokoinSearchHandlerTest {

	@InjectMocks @Spy
	private TokoinSearchHandler underTest;

	@Mock
	private SearchUserHandler searchUserHandler;

	@Mock
	private SearchTicketHandler searchTicketHandler;

	@Mock
	private SearchOrganizationHandler searchOrganizationHandler;
	
	@Test
	public void testSearchProcessShouldCallExecuteSearchWhenInputType1() {
		// given
		doReturn("1").when(underTest).getInput();
		doNothing().when(underTest).executeSearch();

		// when
		underTest.searchProcess();

		// then
		verify(underTest).executeSearch();
		verify(underTest, never()).executeSearchableFields();
	}
	
	@Test
	public void testSearchProcessShouldCallExecuteSearchableFieldsWhenInputType2() {
		// given
		doReturn("2").when(underTest).getInput();
		doNothing().when(underTest).executeSearchableFields();

		// when
		underTest.searchProcess();

		// then
		verify(underTest, never()).executeSearch();
		verify(underTest).executeSearchableFields();
	}
	
	@Test
	public void testSearchProcessShouldDoNothingWhenInputTypeIsNot1Or2() {
		// given
		doReturn("5").when(underTest).getInput();

		// when
		underTest.searchProcess();

		// then
		verify(underTest, never()).executeSearch();
		verify(underTest, never()).executeSearchableFields();
	}
	
	@Test
	public void testExecuteSearchShouldCallUserEexecuteSearchWhenInputTypeIsUser() {
		// given
		doReturn(SearchType.USERS.getValue()).when(underTest).getInput();
		doNothing().when(searchUserHandler).executeSearch();

		// when
		underTest.executeSearch();

		// then
		verify(searchUserHandler).executeSearch();
		verify(searchTicketHandler, never()).executeSearch();
		verify(searchOrganizationHandler, never()).executeSearch();
	}
	
	@Test
	public void testExecuteSearchShouldCallTicketEexecuteSearchWhenInputTypeIsTicket() {
		// given
		doReturn(SearchType.TICKETS.getValue()).when(underTest).getInput();
		doNothing().when(searchTicketHandler).executeSearch();

		// when
		underTest.executeSearch();

		// then
		verify(searchTicketHandler).executeSearch();
		verify(searchUserHandler, never()).executeSearch();
		verify(searchOrganizationHandler, never()).executeSearch();
	}
	
	@Test
	public void testExecuteSearchShouldCallTicketEexecuteSearchWhenInputTypeIsOrganization() {
		// given
		doReturn(SearchType.ORGANIZATION.getValue()).when(underTest).getInput();
		doNothing().when(searchOrganizationHandler).executeSearch();

		// when
		underTest.executeSearch();

		// then
		verify(searchOrganizationHandler).executeSearch();
		verify(searchUserHandler, never()).executeSearch();
		verify(searchTicketHandler, never()).executeSearch();
	}
	
	@Test
	public void testExecuteSearchShouldNotCallExecuteSearchWhenInputTypeIsInvalid() {
		// given
		doReturn("7").when(underTest).getInput();
		doNothing().when(underTest).executeSearch();

		// when
		underTest.executeSearch();

		// then
		verify(underTest).executeSearch();
		verify(searchOrganizationHandler, never()).executeSearch();
		verify(searchUserHandler, never()).executeSearch();
		verify(searchTicketHandler, never()).executeSearch();
	}
	
	@Test
	public void testExecuteSearchableFieldsShouldCallExecuteSearchableFieldsUserWhenInputTypeIsUser() {
		// given
		doReturn(SearchType.USERS.getValue()).when(underTest).getInput();
		doNothing().when(searchUserHandler).executeSearchableFields();

		// when
		underTest.executeSearchableFields();

		// then
		verify(searchUserHandler).executeSearchableFields();
		verify(searchOrganizationHandler, never()).executeSearchableFields();
		verify(searchTicketHandler, never()).executeSearchableFields();
	}
	
	@Test
	public void testExecuteSearchableFieldsShouldCallTicketEexecuteSearchableFieldsWhenInputTypeIsTicket() {
		// given
		doReturn(SearchType.TICKETS.getValue()).when(underTest).getInput();
		doNothing().when(searchTicketHandler).executeSearchableFields();

		// when
		underTest.executeSearchableFields();

		// then
		verify(searchTicketHandler).executeSearchableFields();
		verify(searchUserHandler, never()).executeSearchableFields();
		verify(searchOrganizationHandler, never()).executeSearchableFields();
	}
	
	@Test
	public void testExecuteSearchableFieldsShouldCallTicketExecuteSearchableFieldsWhenInputTypeIsOrganization() {
		// given
		doReturn(SearchType.ORGANIZATION.getValue()).when(underTest).getInput();
		doNothing().when(searchOrganizationHandler).executeSearchableFields();

		// when
		underTest.executeSearchableFields();

		// then
		verify(searchOrganizationHandler).executeSearchableFields();
		verify(searchUserHandler, never()).executeSearchableFields();
		verify(searchTicketHandler, never()).executeSearchableFields();
	}
	
	@Test
	public void testExecuteSearchableFieldsShouldCallAllExecuteSearchSearchableFieldsWhenInputTypeIsAll() {
		// given
		doReturn("4").when(underTest).getInput();
		doNothing().when(searchOrganizationHandler).executeSearchableFields();
		doNothing().when(searchUserHandler).executeSearchableFields();
		doNothing().when(searchTicketHandler).executeSearchableFields();

		// when
		underTest.executeSearchableFields();

		// then
		verify(underTest).executeSearchableFields();
		verify(searchOrganizationHandler).executeSearchableFields();
		verify(searchUserHandler).executeSearchableFields();
		verify(searchTicketHandler).executeSearchableFields();
	}
	
	@Test
	public void testExecuteSearchableFieldsShouldNotCallAnyExecuteSearchSearchableFieldsWhenInputTypeIsInvalid() {
		// given
		doReturn("7").when(underTest).getInput();
		doNothing().when(underTest).executeSearchableFields();

		// when
		underTest.executeSearchableFields();

		// then
		verify(underTest).executeSearchableFields();
		verify(searchOrganizationHandler, never()).executeSearchableFields();
		verify(searchUserHandler, never()).executeSearchableFields();
		verify(searchTicketHandler, never()).executeSearchableFields();
	}
}
