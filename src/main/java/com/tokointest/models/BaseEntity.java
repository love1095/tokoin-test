package com.tokointest.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class BaseEntity {

  public static final String ID_FIELD = "_id";
  public static final String URL_FIELD = "url";
  public static final String EXTERNAL_ID_FIELD = "external_id";
  public static final String CREATED_AT_FIELD = "created_at";
  public static final String TAGS_FIELD = "tags";

  @JsonProperty("_id")
  private String id;

  private String url;

  @JsonProperty("external_id")
  private String externalId;

  @JsonProperty("created_at")
  private String createdAt;

  private List<String> tags;

}
