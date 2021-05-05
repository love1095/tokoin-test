package com.tokointest.core.scanner.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokointest.core.scanner.SearchInterface;
import com.tokointest.core.services.SearchService;
import com.tokointest.core.services.organization.OrganizationService;
import com.tokointest.models.Organization;
import com.tokointest.models.OrganizationField;

@Component
public class SearchOrganizationHandler extends AbstractSearchHandler<Organization> implements SearchInterface {

	private OrganizationService organizationService;

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
