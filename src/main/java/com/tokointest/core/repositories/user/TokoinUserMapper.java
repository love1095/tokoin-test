package com.tokointest.core.repositories.user;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tokointest.core.utils.TokoinJsonReader;

@Repository
public class TokoinUserMapper implements UserMapper{

  @Value("${resource.data.user}")
  private String ticketJsonData;
  
  private JSONArray jsonArray = new JSONArray();

  @Autowired
  private TokoinJsonReader jsonReader;

	@PostConstruct
	public void init() {
		System.out.println("Loading Tickets from: " + ticketJsonData);
		jsonArray = jsonReader.findJsonArray(ticketJsonData);
	}

	@Override
	public JSONArray getJsonArray() {
		return jsonArray;
	}
}