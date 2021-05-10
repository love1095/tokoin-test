package com.tokointest.services.internal;

import java.util.LinkedHashMap;
import java.util.List;

import com.tokointest.models.Ticket;

import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
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

	public <T> List<T> findData(JSONArray json, Class<T> clazz, String term, String value) {
		return findDataFromJson(json, clazz, term, value);
	}

	public LinkedHashMap<String, String> addItemInListToMap(List<String> organizationName, String term) {
		return addItemInListToMapWithKey(organizationName, term);
	}
}
