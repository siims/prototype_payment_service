package eu.onepay.db.resource.data.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.Company;
import eu.onepay.db.data.UniqueFinService;
import eu.onepay.db.resource.data.CompanyResource;
import eu.onepay.db.resource.data.UniqueFinServiceResource;

@Slf4j
@Service
@Transactional
public class UniqueFinServiceResourceImpl extends AbstractResource implements UniqueFinServiceResource {

    @Override
    public UniqueFinService getId(Long finServiceId) {
        UniqueFinService uniqueFinService = (UniqueFinService) session().createCriteria(UniqueFinService.class)
                .add(Restrictions.eq(UniqueFinService.FLD_PRIMARY_FIN_SERVICE_ID, finServiceId))
                .add(Restrictions.eq(UniqueFinService.FLD_ACTIVE, true)).uniqueResult();
        if (uniqueFinService == null) {
            uniqueFinService = (UniqueFinService) session().createCriteria(UniqueFinService.class)
                    .add(Restrictions.eq(UniqueFinService.FLD_ALTERNATIVE_FIN_SERVICE_ID, finServiceId))
                    .add(Restrictions.eq(UniqueFinService.FLD_ACTIVE, true)).uniqueResult();
        }
        return uniqueFinService;
    }
}
