package com.tokointest.repository.internal;

import javax.annotation.PostConstruct;

import com.tokointest.repository.OrganizationRepository;
import com.tokointest.utils.TokoinJsonReader;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Data repository for organization.
 *
 * @author Love
 */
@Repository
public class TokoinOrganizationRepository implements OrganizationRepository {

    @Value("${tokoin.organization}")
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