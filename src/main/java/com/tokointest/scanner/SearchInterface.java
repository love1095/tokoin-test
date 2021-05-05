package com.tokointest.scanner;

public interface SearchInterface {

  String getInput(final boolean checkQuitOption, final String ...validValues);
  void executeSearch();
  void executeSearchableFields();
}
