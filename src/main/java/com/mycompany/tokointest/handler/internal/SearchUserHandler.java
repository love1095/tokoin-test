package com.mycompany.tokointest.handler.internal;

import org.springframework.stereotype.Component;

import com.mycompany.tokointest.handler.SearchInterface;
import com.mycompany.tokointest.models.User;
import com.mycompany.tokointest.models.UserField;
import com.mycompany.tokointest.services.UserService;

@Component
public class SearchUserHandler extends AbstractSearchHandler<User> implements SearchInterface {

  private UserService userService;

  @Override
  public UserService getSearchService() {
    return userService;
  }

  @Override
  public String getEntityName() {
    return User.COLLECTION_NAME;
  }

  @Override
  public void printResultByEntity(final User entity) {
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getName());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getAlias());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.isActive());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.isVerified());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.isShared());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getLocale());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getTimezone());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getLastLoginAt());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getEmail());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getPhone());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getSignature());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.isSuspended());
    System.out.format(LEFT_ALIGN_FORMAT, UserField.ACTIVE, entity.getRole());
  }

}
