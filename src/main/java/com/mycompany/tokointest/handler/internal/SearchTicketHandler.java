package com.mycompany.tokointest.handler.internal;

import org.springframework.stereotype.Component;

import com.mycompany.tokointest.handler.SearchInterface;
import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.Ticket;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.services.SearchService;
import com.mycompany.tokointest.services.TicketsService;

@Component
public class SearchTicketHandler extends AbstractSearchHandler<Ticket> implements SearchInterface {

  private TicketsService ticketService;

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
