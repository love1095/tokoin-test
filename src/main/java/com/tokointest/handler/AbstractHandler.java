package com.tokointest.handler;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tokointest.models.SearchType;

/**
 * Base class for handler.
 *
 * @author Love
 *
 */
public abstract class AbstractHandler implements SearchInterface {

	public static final String INVALID_SEARCH_MESSAGE = "Search option is invalid. Please try again.";

	@Autowired
	protected Scanner scanner;

	@Override
	public String getInput(String... validValues) {
		if (scanner.hasNext()) {
			final String nextValue = scanner.next();
			if (nextValue.equalsIgnoreCase(SearchType.QUIT.getValue())) {
				scanner.close();
				System.exit(0);
			} else {
				if (validValues.length > 0 && !Arrays.asList(validValues).contains(nextValue)) {
					System.err.println(INVALID_SEARCH_MESSAGE);
					return getInput(validValues);
				}
				return nextValue;
			}
		}
		return StringUtils.EMPTY;
	}
}
