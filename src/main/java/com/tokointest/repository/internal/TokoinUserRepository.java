package com.tokointest.repository.internal;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tokointest.repository.UserRepository;
import com.tokointest.utils.TokoinJsonReader;

/**
 * Base class for user repository.
 *
 * @author Love
 */
@Repository
public class TokoinUserRepository implements UserRepository {

	@Value("${resource.data.user}")
	private String collectionName;

	@Autowired
	private TokoinJsonReader jsonReader;

	private JSONArray jsonArray = new JSONArray();

	@PostConstruct
	public void init() {
		jsonArray = jsonReader.findJsonArray(collectionName);
	}

	@Override
	public JSONArray getEntityData() {
		return jsonArray;
	}
}