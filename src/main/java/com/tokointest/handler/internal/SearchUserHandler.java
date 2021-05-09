package com.tokointest.handler.internal;

import org.springframework.stereotype.Component;

import com.tokointest.handler.SearchInterface;
import com.tokointest.models.User;
import com.tokointest.models.UserField;
import com.tokointest.services.SearchService;
import com.tokointest.services.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Class for handle search user.
 *
 * @author Love
 * @param <User>
 *             type of object of search user
 */
@Component @RequiredArgsConstructor
public class SearchUserHandler extends AbstractSearchHandler<User> implements SearchInterface {

	private final UserService userService;

	@Override
	public SearchService<User> getSearchService() {
		return userService;
	}

	@Override
	public String getEntityName() {
		return User.COLLECTION_NAME;
	}

	@Override
	public void printDataBy(final User entity) {
		System.out.format(LEFT_ALIGN, UserField.ID, entity.getId());
		System.out.format(LEFT_ALIGN, UserField.URL, entity.getAlias());
		System.out.format(LEFT_ALIGN, UserField.EXTERNAL_ID, entity.isActive());
		System.out.format(LEFT_ALIGN, UserField.NAME, entity.isVerified());
		System.out.format(LEFT_ALIGN, UserField.ALIAS, entity.isShared());
		System.out.format(LEFT_ALIGN, UserField.CREATED_AT, entity.getLocale());
		System.out.format(LEFT_ALIGN, UserField.ACTIVE, entity.getTimezone());
		System.out.format(LEFT_ALIGN, UserField.VERIFIED, entity.getLastLoginAt());
		System.out.format(LEFT_ALIGN, UserField.SHARED, entity.getEmail());
		System.out.format(LEFT_ALIGN, UserField.LOCALE, entity.getPhone());
		System.out.format(LEFT_ALIGN, UserField.TIMEZONE, entity.getSignature());
		System.out.format(LEFT_ALIGN, UserField.LAST_LOGIN_AT, entity.isSuspended());
		System.out.format(LEFT_ALIGN, UserField.PHONE, entity.getRole());
		System.out.format(LEFT_ALIGN, UserField.SIGNATURE, entity.getSignature());
		System.out.format(LEFT_ALIGN, UserField.ORGANIZATION_ID, entity.isSuspended());
		System.out.format(LEFT_ALIGN, UserField.TAGS, entity.getRole());
		System.out.format(LEFT_ALIGN, UserField.SUSPENDED, entity.isSuspended());
		System.out.format(LEFT_ALIGN, UserField.ROLE, entity.getRole());
	}
}
