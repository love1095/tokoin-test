package com.tokointest.repository.internal;

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
    private TokoinJsonReader tokoinJsonReader;

    private JSONArray jsonArray = new JSONArray();

    @Override
    public void init() {
        jsonArray = tokoinJsonReader.findJsonArray(collectionName);
    }

    @Override
    public JSONArray getEntityData() {
        return jsonArray;
    }
}