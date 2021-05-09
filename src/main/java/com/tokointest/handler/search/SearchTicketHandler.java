package com.tokointest.handler.search;

import org.springframework.stereotype.Component;

import com.tokointest.handler.SearchInterface;
import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.services.SearchService;
import com.tokointest.services.TicketsService;

import lombok.RequiredArgsConstructor;

/**
 * Class for handle search ticket.
 *
 * @author Love
 * @param <Ticket>
 *             type of object of search ticket
 */
@Component @RequiredArgsConstructor
public class SearchTicketHandler extends AbstractSearchHandler<Ticket> implements SearchInterface {

	private final TicketsService ticketService;

	@Override
	public SearchService<Ticket> getSearchService() {
		return ticketService;
	}

	@Override
	public String getEntityName() {
		return Ticket.COLLECTION_NAME;
	}

	@Override
	public void printDataBy(final Ticket entity) {
		System.out.format(LEFT_ALIGN, TicketField.ID, entity.getType());
		System.out.format(LEFT_ALIGN, TicketField.URL, entity.getSubject());
		System.out.format(LEFT_ALIGN, TicketField.EXTERNAL_ID, entity.getDescription());
		System.out.format(LEFT_ALIGN, TicketField.CREATED_AT, entity.getPriority());
		System.out.format(LEFT_ALIGN, TicketField.TYPE, entity.getStatus());
		System.out.format(LEFT_ALIGN, TicketField.SUBJECT, entity.getDueAt());
		System.out.format(LEFT_ALIGN, TicketField.DESCRIPTION, entity.getVia());
		System.out.format(LEFT_ALIGN, TicketField.PRIORITY, entity.getSubmitterId());
		System.out.format(LEFT_ALIGN, TicketField.STATUS, entity.getAssigneeId());
		System.out.format(LEFT_ALIGN, TicketField.SUBMITTER_ID, entity.getDescription());
		System.out.format(LEFT_ALIGN, TicketField.ASSIGNEE_ID, entity.getPriority());
		System.out.format(LEFT_ALIGN, TicketField.ORGANIZATION_ID, entity.getStatus());
		System.out.format(LEFT_ALIGN, TicketField.TAGS, entity.getDueAt());
		System.out.format(LEFT_ALIGN, TicketField.HAS_INCIDENTS, entity.getVia());
		System.out.format(LEFT_ALIGN, TicketField.DUE_AT, entity.getSubmitterId());
		System.out.format(LEFT_ALIGN, TicketField.VIA, entity.getAssigneeId());
	}
}
