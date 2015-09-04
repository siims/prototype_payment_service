package eu.onepay.db.resource.data.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.Company;
import eu.onepay.db.resource.data.CompanyResource;

@Slf4j
@Service
@Transactional
public class CompanyResourceImpl extends AbstractCleanResource implements CompanyResource {

    @Override
    public Integer companyId(String registrationCode, Integer countryCode) {
        Company company = (Company) session().createCriteria(Company.class).add(
                Restrictions.eq(Company.FLD_REG_CODE, registrationCode)).add(
                Restrictions.eq(Company.FLD_COUNTRY_ID, countryCode)).uniqueResult();
        return getResultRowIdCode(company);
    }

    @Override
    public boolean deactivateCompany(int companyId) {
        session().createQuery("UPDATE Company SET active = false WHERE id = :companyId")
                .setInteger("companyId", companyId).executeUpdate();
        return true;
    }

    @Override
    public int storeCompany(Company company) {
        return store(company);
    }
}
