package com.tokointest.handler.search;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.annotations.VisibleForTesting;
import com.tokointest.handler.AbstractHandler;
import com.tokointest.models.BaseEntity;
import com.tokointest.models.DataResponse;
import com.tokointest.services.SearchService;

/**
 * Base class for search handler.
 *
 * @author Love
 * @param <T>
 *             type of object of search entity
 */
public abstract class AbstractSearchHandler<T extends BaseEntity> extends AbstractHandler {

	public static final String SEARCH_TERM = "Enter search term";
	public static final String SEARCH_VALUE = "Enter search value";
	public static final String HR_LINE = "-----------------------------------------------------------------------------------------------------------------------------------%n";
	public static final String LEFT_ALIGN = " %-35s   %-100s %n";
	public static final String COLUMN_VALUE = "| Field                                | Value %n";
	public static final String NO_RESULTS = "No results found";

	abstract SearchService<T> getSearchService();

	abstract String getEntityName();

	abstract void printDataBy(final T entity);

	@Override
	public void executeSearch() {
		System.out.println(SEARCH_TERM);
		List<String> searchableFields = getSearchService().getSearchableFields();
		final String searchTerm = getInput(
				Arrays.copyOf(searchableFields.toArray(), searchableFields.size(), String[].class));
		System.out.println(SEARCH_VALUE);
		final String searchValue = getInput();
		System.out.println(
				String.format("Searching %s for %s with a value of %s", getEntityName(), searchTerm, searchValue));
		List<DataResponse<T>> responses = getSearchService().process(searchTerm, searchValue);
		printResults(responses);
	}

	@Override
	public void executeSearchableFields() {
		System.out.println(String.format("Search %s with", getEntityName()));
		getSearchService().getSearchableFields().forEach(System.out::println);
	}

	@VisibleForTesting
	void printResults(final List<DataResponse<T>> responses) {
		if (responses.isEmpty()) {
			System.out.println(NO_RESULTS);
		} else {
			System.out.format(HR_LINE);
			System.out.format(COLUMN_VALUE);
			System.out.format(HR_LINE);
			responses.forEach(response -> {
				printDataBy(response.getEntity());
				for (Entry<String, String> element : response.getRelatedEntities().entrySet()) {
					System.out.format(LEFT_ALIGN, element.getKey(), element.getValue());
				}
			});
		}
	}
}
