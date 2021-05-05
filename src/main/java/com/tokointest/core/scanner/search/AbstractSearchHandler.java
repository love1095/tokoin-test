package com.tokointest.core.scanner.search;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tokointest.core.scanner.AbstractHandler;
import com.tokointest.core.services.SearchService;
import com.tokointest.models.BaseEntity;

public abstract class AbstractSearchHandler<T extends BaseEntity> extends AbstractHandler {

	public static final String SEARCH_TERM = "Enter search term";
	public static final String SEARCH_VALUE = "Enter search value";
	public static final String HR_LINE = "+---------------------------+------------------------------------------------------------------------------------------------------+%n";
	public static final String LEFT_ALIGN_FORMAT = "| %-25s | %-100s %n";
	public static final String COLUMN_VALUE = "| Column Name     | Value %n";
	public static final String NO_RESULTS = "No results found";

	abstract SearchService<T> getSearchService();

	abstract String getEntityName();

	abstract void printResultByEntity(final T entity);

	@Override
	public void executeSearch() {
		System.out.println(SEARCH_TERM);
		final List<String> searchableFields = getSearchService().getSearchableFields();
		final String searchTerm = getInput(true,
				Arrays.copyOf(searchableFields.toArray(), searchableFields.size(), String[].class));
		System.out.println(SEARCH_VALUE);
		final String searchValue = getInput(true);
		System.out.println("Searching " + getEntityName() + " for " + searchTerm + " with a value of " + searchValue);
		try {
			getSearchService().userSearchProcess(null, searchTerm, searchValue);
			printResults(null);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			} // NOPMD
			this.executeSearch();
		}
	}

	private void printResults(final List<T> entities) {
		if (entities.isEmpty()) {
			System.out.println(NO_RESULTS);
		} else {
			System.out.format(HR_LINE);
			System.out.format(COLUMN_VALUE);
			System.out.format(HR_LINE);
			entities.forEach(ticket -> {
				System.out.format(LEFT_ALIGN_FORMAT, BaseEntity.ID_FIELD, ticket.getId());
				System.out.format(LEFT_ALIGN_FORMAT, BaseEntity.URL_FIELD, ticket.getUrl());
				System.out.format(LEFT_ALIGN_FORMAT, BaseEntity.EXTERNAL_ID_FIELD, ticket.getExternalId());
				System.out.format(LEFT_ALIGN_FORMAT, BaseEntity.CREATED_AT_FIELD, ticket.getCreatedAt());
				System.out.format(LEFT_ALIGN_FORMAT, BaseEntity.TAGS_FIELD, ticket.getTags());
				printResultByEntity(ticket);
				System.out.format(HR_LINE);
			});
		}
	}

	  @Override
	  public void executeSearchableFields() {
	    System.out.println("Search " + getEntityName() + " with");
	    getSearchService().getSearchableFields().forEach(System.out::println);
	  }
}
