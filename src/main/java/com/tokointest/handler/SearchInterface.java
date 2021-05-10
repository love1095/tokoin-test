package com.tokointest.handler;

/**
 * Interface layer for handle search.
 *
 * @author Love
 */
public interface SearchInterface {

    /**
     * Gets value of input from application.
     *
     * @param validValues
     *            the given valid values for input string
     * @return string of input value
     */
	String getInput(String... validValues);

    /**
     * Executes search process from repository.
     */
	void executeSearch();

    /**
     * Executes search SearchableFields.
     */
	void executeSearchableFields();
}
