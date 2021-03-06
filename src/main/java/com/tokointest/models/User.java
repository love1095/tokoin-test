package com.tokointest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for user from database.
 *
 * @author Love
 */
@NoArgsConstructor @Getter
public class User extends BaseEntity {

	public static final String COLLECTION_NAME = "Users";

	@JsonProperty("_id")
	private int id;

	private String name;

	private String alias;

	private boolean active;

	private boolean verified;

	private boolean shared;

	private String locale;

	private String timezone;

	@JsonProperty("last_login_at")
	private String lastLoginAt;

	private String email;

	private String phone;

	private String signature;

	@JsonProperty("organization_id")
	private int organizationId;

	private boolean suspended;

	private String role;
}