package com.tokointest.core.scanner;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHandler implements SearchInterface {

  public static final String QUIT_OPTION = "quit";
  public static final String INVALID_INPUT_VALUE = "Input value is invalid. Please try again";

  @Autowired
  protected Scanner scanner;

  @Override
  public String getInput(final boolean checkQuitOption, final String... validValues) {
    if (scanner.hasNext()) {
      final String nextValue = scanner.next();
      if (checkQuitOption && nextValue.equals(QUIT_OPTION)) {
        scanner.close();
        System.exit(0); // NOPMD
      } else {

        if (validValues.length > 0 && !Arrays.asList(validValues).contains(nextValue)) {
          System.err.println(INVALID_INPUT_VALUE);
          return getInput(checkQuitOption, validValues);
        }
        return nextValue;
      }
    }
    return StringUtils.EMPTY;
  }

	@Override
	public void executeSearchableFields() {
	}
}
