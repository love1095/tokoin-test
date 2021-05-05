package com.mycompany.tokointest.services;

import java.util.List;

import com.mycompany.tokointest.models.BaseEntity;
import com.mycompany.tokointest.models.TokoinType;

public interface SearchService<T extends BaseEntity, M> {

	List<String> userSearchProcess(TokoinType type, M field, String value) throws Exception;
}
