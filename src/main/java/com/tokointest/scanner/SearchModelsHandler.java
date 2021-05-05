package com.tokointest.scanner;

import org.springframework.stereotype.Component;

import com.tokointest.scanner.search.SearchOrganizationHandler;
import com.tokointest.scanner.search.SearchTicketHandler;
import com.tokointest.scanner.search.SearchUserHandler;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchModelsHandler extends AbstractHandler {

  private static final String SEARCH_MODEL_OPTIONS = "\nPlease select search model:\n"
      + "* Press 1 to search Users\n"
      + "* Press 2 to search Tickets\n"
      + "* Press 3 to search Organizations";

  private static final String SEARCHABLE_MODEL_OPTIONS = "\nPlease select searchable model:\n"
      + "* Press 1 to get searchable fields of Users\n"
      + "* Press 2 to get searchable fields of Tickets\n"
      + "* Press 3 to get searchable fields of Organizations";

	private static final String SELECTED_OPTION_ERROR = "Selected option is invalid. Please try again.";

	private final SearchUserHandler searchUserHandler;
	private final SearchTicketHandler searchTicketHandler;
	private final SearchOrganizationHandler searchOrganizationHandler;

  @Override
  public void executeSearch() {
    System.out.println(SEARCH_MODEL_OPTIONS);
    switch (getInput(true)) {
      case "1":
    	  searchUserHandler.executeSearch();
        break;
      case "2":
        searchTicketHandler.executeSearch();
        break;
      case "3":
        searchOrganizationHandler.executeSearch();
        break;
      default:
        System.err.println(SELECTED_OPTION_ERROR);
        executeSearch();
        break;
    }
  }

  @Override
  public void executeSearchableFields() {
    System.out.println(SEARCHABLE_MODEL_OPTIONS);
    switch (getInput(true)) {
      case "1":
        searchUserHandler.executeSearchableFields();
        break;
      case "2":
        searchTicketHandler.executeSearchableFields();
        break;
      case "3":
        searchOrganizationHandler.executeSearchableFields();
        break;
      default:
        System.err.println(SELECTED_OPTION_ERROR);
        executeSearchableFields();
        break;
    }
  }
}
