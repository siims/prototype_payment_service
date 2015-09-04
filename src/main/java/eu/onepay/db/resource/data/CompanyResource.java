package eu.onepay.db.resource.data;

import eu.onepay.db.data.Company;


public interface CompanyResource extends CRUDResource {

    public Integer companyId(String registrationCode, Integer countryCode);

    public boolean deactivateCompany(int companyId);

    public int storeCompany(Company company);

}
