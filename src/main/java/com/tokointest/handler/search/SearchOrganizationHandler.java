package com.tokointest.handler.search;

import org.springframework.stereotype.Component;

import com.tokointest.handler.SearchInterface;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.services.OrganizationService;
import com.tokointest.services.SearchService;

import lombok.RequiredArgsConstructor;

/**
 * Class for handle search organization.
 *
 * @author Love
 * @param <Organization>
 *             type of object of search organization
 */
@Component @RequiredArgsConstructor
public class SearchOrganizationHandler extends AbstractSearchHandler<Organization> implements SearchInterface {

	private final OrganizationService organizationService;

	@Override
	public SearchService<Organization> getSearchService() {
		return organizationService;
	}

	@Override
	public String getEntityName() {
		return Organization.COLLECTION_NAME;
	}

	@Override
	public void printDataBy(final Organization entity) {
		System.out.format(LEFT_ALIGN, OrganizationField.ID, entity.getName());
		System.out.format(LEFT_ALIGN, OrganizationField.URL, entity.getDomainNames());
		System.out.format(LEFT_ALIGN, OrganizationField.EXTERNAL_ID, entity.getDetails());
		System.out.format(LEFT_ALIGN, OrganizationField.ORGANIZATION_NAME, entity.isSharedTickets());
		System.out.format(LEFT_ALIGN, OrganizationField.DOMAIN_NAMES, entity.getName());
		System.out.format(LEFT_ALIGN, OrganizationField.CREATED_AT, entity.getDomainNames());
		System.out.format(LEFT_ALIGN, OrganizationField.EXTERNAL_ID, entity.getDetails());
		System.out.format(LEFT_ALIGN, OrganizationField.DETAILS, entity.isSharedTickets());
		System.out.format(LEFT_ALIGN, OrganizationField.SHARED_TICKETS, entity.getName());
		System.out.format(LEFT_ALIGN, OrganizationField.TAGS, entity.getDomainNames());
	}
}
