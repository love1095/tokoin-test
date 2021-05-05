package com.mycompany.tokointest.handler;

import org.springframework.stereotype.Component;

@Component
public class MainHandler extends AbstractHandler {

  private static final String INTRODUCTION = "\nType 'quit' to exit at any time. Press 'Enter' to continue";
  private static final String INTRODUCTION_OPTIONS = "Select search options:\n"
      + "* Press 1 to search\n"
      + "* Press 2 to view a list of searchable fields\n"
      + "* Type 'quit' to exit";

  private SearchModelsHandler searchModelsHandler;

  @Override
  public void executeSearch() {
    System.out.println(INTRODUCTION);
    boolean isContinue = true;

    do {
      System.out.println();
      System.out.println(INTRODUCTION_OPTIONS);
      final String input = getInput(false);
      switch (input) {
        case "1":
          searchModelsHandler.executeSearch();
          break;
        case "2":
            searchModelsHandler.executeSearchableFields();
            break;
        case QUIT_OPTION:
          isContinue = false;
          break;
        default:
          System.err.println("Selected option is invalid. Please try again.");
          break;
      }
    } while (isContinue);
  }
}
