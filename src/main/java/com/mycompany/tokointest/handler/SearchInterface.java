package com.mycompany.tokointest.handler;

public interface SearchInterface {

  String getInput(final boolean checkQuitOption, final String ...validValues);
  void executeSearch();
  void executeSearchableFields();
}
