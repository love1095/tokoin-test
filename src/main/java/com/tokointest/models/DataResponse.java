package com.tokointest.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Class for data response data.
 *
 * @author Love
 */
@AllArgsConstructor @Getter
public class DataResponse<T> {

	private T entity;
	private Map<String, String> relatedEntities;
}