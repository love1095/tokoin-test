package com.tokointest.services;

import java.util.List;

import com.tokointest.models.BaseEntity;
import com.tokointest.models.DataResponse;

/**
 * Service for handling base data.
 *
 * @author Love
 */
public interface SearchService<T extends BaseEntity> {

    /**
     * Process search by term and value.
     *
     * @param type
     *            the given search type
     * @param value
     *            the given search value
     * @return list {@link DataResponse} of {@link T}
     */
	List<DataResponse<T>> process(String type, String value);


    /**
     * Gets searchable fields.
     *
     * @return list of {@link String}
     */
	List<String> getSearchableFields();

    /**
     * Checks is correct value by term.
     *
     * @param type
     *            the given search type
     * @param value
     *            the given search value
     * @return return true if is correct field, otherwise return false
     */
	boolean isCorrectFields(String term, String value);


    /**
     * Inits data from json files.
     */
    void initDatas();
}
