package com.tokointest.services.internal;

import java.util.LinkedHashMap;
import java.util.List;

import com.tokointest.models.Ticket;

import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.stereotype.Service;

import static com.tokointest.utils.TokoinJsonReader.findDataFromJson;
import static com.tokointest.utils.TokoinUtils.addItemInListToMapWithKey;

/**
 * Search implement of {@link Ticket}.
 *
 * @author Love
 * @param <Ticket>
 *             type of object of ticket
 */
@Service @RequiredArgsConstructor
public abstract class AbstractService {

    /**
     * Finds data by value and term from json files.
     *
     * @param term
     *            the given json array
     * @param clazz
     *            the given clazz type of return class
     * @param term
     *            the given search type
     * @param value
     *            the given search value
     * @return list of {@link T}
     */
	public <T> List<T> findData(JSONArray json, Class<T> clazz, String term, String value) {
		return findDataFromJson(json, clazz, term, value);
	}

    /**
     * Adds item in list to a map.
     *
     * @param organizationName
     *            the given list of items need to add
     * @param term
     *            the given search type
     * @return hash map of string value by string key
     */
	public LinkedHashMap<String, String> addItemInListToMap(List<String> items, String term) {
		return addItemInListToMapWithKey(items, term);
	}
}
