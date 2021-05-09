package com.tokointest.repository.internal;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tokointest.repository.OrganizationRepository;
import com.tokointest.utils.TokoinJsonReader;

/**
 * Data repository for organization.
 *
 * @author Love
 */
@Repository
public class TokoinOrganizationRepositroy implements OrganizationRepository {

	@Value("${resource.data.organization}")
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