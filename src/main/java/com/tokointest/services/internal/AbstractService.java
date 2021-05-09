package com.tokointest.services.internal;

import static com.tokointest.utils.TokoinJsonReader.findDataFromJson;
import static com.tokointest.utils.TokoinUtils.addItemInListToMapWithKey;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.tokointest.models.Ticket;

import lombok.RequiredArgsConstructor;

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
