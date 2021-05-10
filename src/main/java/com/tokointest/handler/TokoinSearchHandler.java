package com.tokointest.handler;

import com.tokointest.handler.internal.SearchOrganizationHandler;
import com.tokointest.handler.internal.SearchTicketHandler;
import com.tokointest.handler.internal.SearchUserHandler;
import com.tokointest.models.SearchType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tokointest.models.SearchType.QUIT;

/**
 * Class for handle search process.
 *
 * @author Love
 */
@Component()
public class TokoinSearchHandler extends AbstractHandler {

    private static final String START_MESSAGE = "\nType '%s' to exit at any time. Press 'Enter' to continue";
    private static final String START_OPTIONS_MESSAGE = "        Select search options:\n"
      + "        * Press 1 to search\n"
      + "        * Press 2 to view a list of searchable fields\n"
      + "        * Type '%s' to exit";
	private static final String DASH_LINE = "\n---------------------------------------------------------------------------------------------------------------------------------";
    private static final String SEARCH_TYPE = "\nSelect 1) Users or 2) Tickets or 3) Organizations";
    private static final String SEARCHABLE_TYPE = "\nSelect 1) Users or 2) Tickets or 3) Organizations 4) All";

    @Autowired
    private SearchUserHandler searchUserHandler;

    @Autowired
    private SearchTicketHandler searchTicketHandler;

    @Autowired
    private SearchOrganizationHandler searchOrganizationHandler;


    /**
     * Process for search data from repository.
     */
    public void searchProcess() {
		System.out.println(String.format(START_MESSAGE, QUIT.getValue()));
		System.out.println();
		System.out.println(String.format(START_OPTIONS_MESSAGE, QUIT.getValue()));
		final String input = getInput();
		switch (input) {
		case "1":
			executeSearch();
			break;
		case "2":
			executeSearchableFields();
			break;
		default:
			System.err.println(INVALID_SEARCH_MESSAGE);
			break;
		}
	}

	@Override
	public void executeSearch() {
		System.out.println(SEARCH_TYPE);
		switch (SearchType.findBy(getInput())) {
		case USERS:
			searchUserHandler.executeSearch();
			break;
		case TICKETS:
			searchTicketHandler.executeSearch();
			break;
		case ORGANIZATION:
			searchOrganizationHandler.executeSearch();
			break;
		case INVALID:
		default:
			System.err.println(INVALID_SEARCH_MESSAGE);
			executeSearch();
			break;
		}
	}

	@Override
	public void executeSearchableFields() {
		System.out.println(SEARCHABLE_TYPE);
		switch (SearchType.findBy(getInput())) {
		case USERS:
			searchUserHandler.executeSearchableFields();
			break;
		case TICKETS:
			searchTicketHandler.executeSearchableFields();
			break;
		case ORGANIZATION:
			searchOrganizationHandler.executeSearchableFields();
			break;
		case ALL:
			searchUserHandler.executeSearchableFields();
			System.out.println(DASH_LINE);
			searchTicketHandler.executeSearchableFields();
			System.out.println(DASH_LINE);
			searchOrganizationHandler.executeSearchableFields();
			break;
		case INVALID:
		default:
			System.err.println(INVALID_SEARCH_MESSAGE);
			executeSearchableFields();
			break;
		}
	}

}
