package com.mycompany.tokointest.read;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TokionService.
 *
 * @author Love
 *
 */
public class TokoinJsonReader {
    
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonReader(String fileName, Class <T> clazz) throws Exception {
		File customer = getCustomerFileReader.apply(fileName);
		JSONParser parser = new JSONParser();

		List<T> cus = null;
		try (Reader is = new FileReader(customer)) {
			JSONArray jsonArray = (JSONArray) parser.parse(is);
			cus = (List<T>) jsonArray.stream().map(json -> {
				try {
					return getObject(json, clazz);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).collect(Collectors.toList());

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return cus;
	}
    
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonReader(String fileName, Class <T> clazz, String type, String value) throws Exception {
		File customer = getCustomerFileReader.apply(fileName);
		JSONParser parser = new JSONParser();

		List<T> cus = new LinkedList<>();
		try (Reader is = new FileReader(customer)) {
			JSONArray jsonArray = (JSONArray) parser.parse(is);
			ObjectMapper mapper = new ObjectMapper();
			for (Object eachCropJson : jsonArray) {
				HashMap<String, Object> eachCropMap = (HashMap<String, Object>) mapper.convertValue(eachCropJson,
						HashMap.class);
				if (eachCropMap.get(type) != null && String.valueOf(eachCropMap.get(type)).contains(value)) {
					cus.add(getObject(eachCropJson, clazz));
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return cus;
	}

	public static JSONArray findJsonArray(String fileName) {
		File customer = getCustomerFileReader.apply(fileName);
		JSONParser parser = new JSONParser();

		try (Reader is = new FileReader(customer)) {
			return (JSONArray) parser.parse(is);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> searchProcess(JSONArray jsonArray, Class<T> clazz, String type, String value)
			throws Exception {
		List<T> cus = new LinkedList<>();
		ObjectMapper mapper = new ObjectMapper();
		for (Object eachCropJson : jsonArray) {
			HashMap<String, Object> eachCropMap = (HashMap<String, Object>) mapper.convertValue(eachCropJson,
					HashMap.class);
			if (eachCropMap.get(type) != null && String.valueOf(eachCropMap.get(type)).contains(value)) {
				cus.add(getObject(eachCropJson, clazz));
			}
		}
		return cus;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> searchIdProcess(JSONArray jsonArray, Class<T> clazz, String type, int value)
			throws Exception {
		List<T> cus = new LinkedList<>();
		ObjectMapper mapper = new ObjectMapper();
		for (Object eachCropJson : jsonArray) {
			HashMap<String, Object> eachCropMap = (HashMap<String, Object>) mapper.convertValue(eachCropJson,
					HashMap.class);
			if (eachCropMap.get(type) != null && Integer.parseInt(String.valueOf(eachCropMap.get(type))) == value) {
				cus.add(getObject(eachCropJson, clazz));
			}
		}
		return cus;
	}

	static <T> T getObject(Object json, Class <T> clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json.toString(), clazz);
	}

	static Function<String, File> getCustomerFileReader = filename -> {
		ClassLoader cl = TokoinJsonReader.class.getClassLoader();
		return new File(cl.getResource(filename).getFile());
	};

}
