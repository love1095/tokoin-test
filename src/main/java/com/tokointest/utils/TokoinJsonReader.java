package com.tokointest.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class for read json file.
 *
 * @author Love
 *
 */

@Slf4j @Component @RequiredArgsConstructor
public class TokoinJsonReader {

	public JSONArray findJsonArray(String fileName) {
        try {
            return (JSONArray) new JSONParser().parse(new InputStreamReader(
                    TokoinJsonReader.class.getResourceAsStream(fileName)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
	}

	@VisibleForTesting
	JSONArray parseToJsonArray(JSONParser parser, Reader is) throws IOException, ParseException {
		return (JSONArray) parser.parse(is);
	}

	@VisibleForTesting
	Reader initFileReader(File file) throws FileNotFoundException {
		return new FileReader(file);
	}

	@VisibleForTesting
	File findFileBy(String fileName) {
		ClassLoader classLoader = TokoinJsonReader.class.getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}

	public static <T> List<T> findDataFromJson(JSONArray array, Class<T> clazz, String term, String value) {
		try {
			return searchProcess(array, clazz, term, value);
		} catch (Exception e) {
			e.printStackTrace();
			getLogger().error("Find data by json array has error", e);
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> searchProcess(JSONArray jsonArray, Class<T> clazz, String term, String value)
			throws Exception {
		List<T> cus = new LinkedList<>();
		ObjectMapper mapper = new ObjectMapper();
		for (Object eachCropJson : jsonArray) {
			HashMap<String, Object> eachCropMap = mapper.convertValue(eachCropJson,
					HashMap.class);
			if (eachCropMap.get(term) != null && isSearchValue(value, eachCropMap.get(term))) {
				cus.add(getObject(eachCropJson, clazz));
			}
		}
		return cus;
	}

	@VisibleForTesting
	static boolean isSearchValue(String value, Object term) throws Exception {
		if (isNumeric(String.valueOf(term))) {
			return (Long) term == Long.parseLong(value);
		}
		return String.valueOf(term).contains(value);
	}

	@VisibleForTesting
	static boolean isNumeric(String str) {
		return NumberUtils.isDigits(str);
	}

	@VisibleForTesting
	static <T> T getObject(Object json, Class<T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json.toString(), clazz);
	}

    @VisibleForTesting
	static Logger getLogger() {
        return log;
    }
}
