package com.mycompany.tokointest.services;

import static com.mycompany.tokointest.read.TokoinJsonReader.jsonReader;

import java.util.List;

import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.User;
import com.mycompany.tokointest.models.UserField;

/**
 * TokionService.
 *
 * @author Love
 *
 */
public class TokoinUserService {

	private static final String USER_JSON = "users.json";

	public static void userSearchProcess(TokoinType type, UserField fields, String value) throws Exception {
		List<User> organization = jsonReader(USER_JSON, User.class);
	}
}
