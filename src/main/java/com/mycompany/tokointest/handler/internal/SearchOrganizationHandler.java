package com.mycompany.tokointest.handler.internal;

import org.springframework.stereotype.Component;

import com.mycompany.tokointest.handler.SearchInterface;
import com.mycompany.tokointest.models.Organization;
import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.services.OrganizationService;
import com.mycompany.tokointest.services.SearchService;

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
