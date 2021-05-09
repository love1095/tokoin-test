package com.tokointest.utils;

import java.util.LinkedHashMap;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for Tokoin.
 *
 * @author Love
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokoinUtils {

	public static <T> LinkedHashMap<String, T> addItemInListToMapWithKey(List<T> items, String key) {
		LinkedHashMap<String, T> result = new LinkedHashMap<>();
		if (items == null || items.isEmpty()) {
			return result;
		}
		for (int i = 0; i < items.size(); i++) {
			result.put(key + "_" + i, items.get(i));
		}
		return result;
	}
}
