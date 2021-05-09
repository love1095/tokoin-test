package com.tokointest.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Base class for base entity.
 *
 * @author Love
 */
@Getter @NoArgsConstructor
public class BaseEntity {

	private String url;

	@JsonProperty("external_id")
	private String externalId;

	@JsonProperty("created_at")
	private String createdAt;

	private List<String> tags;

}
