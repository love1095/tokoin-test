package com.mycompany.tokointest.services;

import static com.mycompany.tokointest.read.TokoinJsonReader.searchProcess;
import static com.mycompany.tokointest.read.TokoinJsonReader.findJsonArray;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.mycompany.tokointest.models.Ticket;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TokoinType;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
public class TokoinTicketsService {

	private static final String TICKETS_JSON = "tickets.json";

	public static void userSearchProcess(TokoinType type, TicketField field, String value) throws Exception {
		JSONArray array = findJsonArray(TICKETS_JSON);
		List<Ticket> tickets = searchProcess(array, Ticket.class, field.name().toLowerCase(), value);
	}
}
