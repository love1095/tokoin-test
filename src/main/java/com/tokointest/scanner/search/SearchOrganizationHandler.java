package com.tokointest.scanner.search;

import org.springframework.stereotype.Component;

import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;
import com.tokointest.scanner.SearchInterface;
import com.tokointest.services.SearchService;
import com.tokointest.services.organization.OrganizationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
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
	public void printResultByEntity(final Organization entity) {
		System.out.format(LEFT_ALIGN_FORMAT, OrganizationField.DOMAIN_NAMES, entity.getName());
		System.out.format(LEFT_ALIGN_FORMAT, OrganizationField.DOMAIN_NAMES, entity.getDomainNames());
		System.out.format(LEFT_ALIGN_FORMAT, OrganizationField.DOMAIN_NAMES, entity.getDetails());
		System.out.format(LEFT_ALIGN_FORMAT, OrganizationField.DOMAIN_NAMES, entity.isSharedTickets());
	}
}
