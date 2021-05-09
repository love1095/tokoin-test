package com.tokointest.handler;

/**
 * Interface layer for handle search.
 *
 * @author Love
 */
public interface SearchInterface {

	String getInput(String... validValues);

	void executeSearch();

	void executeSearchableFields();
}
