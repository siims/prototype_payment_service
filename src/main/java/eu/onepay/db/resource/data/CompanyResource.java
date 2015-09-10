package eu.onepay.db.resource.data;

import eu.onepay.db.data.Company;


public interface CompanyResource extends CRUDResource {

    public Long companyId(String registrationCode, Integer countryCode);

    public boolean deactivateCompany(long companyId);

    public long storeCompany(Company company);

}
