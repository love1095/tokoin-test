package com.mycompany.tokointest.services;

import java.util.List;

import com.mycompany.tokointest.models.BaseEntity;
import com.mycompany.tokointest.models.TokoinType;

public interface SearchService<T extends BaseEntity> {

	List<String> userSearchProcess(TokoinType type, String field, String value) throws Exception;

	List<String> getSearchableFields();
}
