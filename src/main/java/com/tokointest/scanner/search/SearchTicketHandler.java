package com.tokointest.scanner.search;

import org.springframework.stereotype.Component;

import com.tokointest.models.Ticket;
import com.tokointest.models.TicketField;
import com.tokointest.scanner.SearchInterface;
import com.tokointest.services.SearchService;
import com.tokointest.services.ticket.TicketsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
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
  public void printResultByEntity(final Ticket entity) {
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getType());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getSubject());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getDescription());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getPriority());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getStatus());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getDueAt());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getVia());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getSubmitterId());
    System.out.format(LEFT_ALIGN_FORMAT, TicketField.ASSIGNEE_ID, entity.getAssigneeId());
  }
}
