package com.tokointest.core.services;

import java.util.List;

import com.tokointest.models.BaseEntity;
import com.tokointest.models.TokoinType;

public interface SearchService<T extends BaseEntity> {

	List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception;

	List<String> getSearchableFields();
}
