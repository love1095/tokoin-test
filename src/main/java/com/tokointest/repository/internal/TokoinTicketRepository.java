package com.tokointest.repository.internal;

import javax.annotation.PostConstruct;

import com.tokointest.repository.TicketRepository;
import com.tokointest.utils.TokoinJsonReader;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Base class for ticket repository.
 *
 * @author Love
 */
@Repository
public class TokoinTicketRepository implements TicketRepository {

    @Value("${tokoin.ticket}")
	private String collectionName;

	private JSONArray jsonArray = new JSONArray();

	@Autowired
	private TokoinJsonReader jsonReader;

	@PostConstruct
	public void init() {
		jsonArray = jsonReader.findJsonArray(collectionName);
	}

	@Override
	public JSONArray getEntityData() {
		return jsonArray;
	}
}