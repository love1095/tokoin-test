package com.tokointest.repositories.ticket;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tokointest.utils.TokoinJsonReader;


@Repository
public class TokoinTicketMapper implements TicketMapper{

  @Value("${resource.data.ticket}")
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