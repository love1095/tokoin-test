package com.tokointest.repository;

import org.json.simple.JSONArray;


/**
 * Data repository for base data.
 *
 * @author Love
 */
public interface BaseRepository {

	JSONArray getEntityData();

    void init();
}
