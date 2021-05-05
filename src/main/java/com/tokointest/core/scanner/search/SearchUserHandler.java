package com.tokointest.core.scanner.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokointest.core.scanner.SearchInterface;
import com.tokointest.core.services.user.UserService;
import com.tokointest.models.User;
import com.tokointest.models.UserField;

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
