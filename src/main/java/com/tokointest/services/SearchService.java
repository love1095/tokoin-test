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

	List<DataResponse<T>> process(String type, String value);

	List<String> getSearchableFields();
}
