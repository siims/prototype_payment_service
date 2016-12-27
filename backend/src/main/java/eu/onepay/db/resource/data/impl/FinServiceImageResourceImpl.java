package eu.onepay.db.resource.data.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.Company;
import eu.onepay.db.data.FinServiceImage;
import eu.onepay.db.data.UniqueFinService;
import eu.onepay.db.resource.data.CompanyResource;
import eu.onepay.db.resource.data.FinServiceImageResource;
import eu.onepay.db.resource.data.UniqueFinServiceResource;

@Slf4j
@Service
@Transactional
public class FinServiceImageResourceImpl extends AbstractResource implements FinServiceImageResource {

    @Override
    public String getUrl(Long id) {
        return getById(FinServiceImage.class, id).getUrl();
    }
}
