package com.mycompany.tokointest.services;

import static com.mycompany.tokointest.read.TokoinJsonReader.findJsonArray;
import static com.mycompany.tokointest.read.TokoinJsonReader.searchIdProcess;
import static com.mycompany.tokointest.read.TokoinJsonReader.searchProcess;

import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.models.Ticket;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.User;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
public class TokoinOrganizationService {

	private static final String ORGANIZATIONS_JSON = "organizations.json";
	private static final String TICKETS_JSON = "tickets.json";
	private static final String USER_JSON = "users.json";

	public static void userSearchProcess(TokoinType type, OrganizationField field, String value) throws Exception {
		List<Organization> organizations = findDataFromJson(ORGANIZATIONS_JSON, Organization.class,
				field.name().toLowerCase(), value);
		if (!organizations.isEmpty()) {
			for (Organization organization : organizations) {
				List<Ticket> tickets = findIdDataFromJson(TICKETS_JSON, Ticket.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), organization.getId());
				List<User> user = findIdDataFromJson(USER_JSON, User.class,
						TicketField.ORGANIZATION_ID.name().toLowerCase(), organization.getId());
			}

		}
	}
	
	static <T> List<T> findDataFromJson(String fileName, Class<T> clazz, String type, String value) {
		JSONArray array = findJsonArray(ORGANIZATIONS_JSON);
		try {
			return searchProcess(array, clazz, type, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	static <T> List<T> findIdDataFromJson(String fileName, Class<T> clazz, String type, int value) {
		JSONArray array = findJsonArray(ORGANIZATIONS_JSON);
		try {
			return searchIdProcess(array, clazz, type, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
