package eu.onepay.db.resource.data.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.Company;
import eu.onepay.db.data.FinServiceImage;
import eu.onepay.db.data.FinServicePubKey;
import eu.onepay.db.data.UniqueFinService;
import eu.onepay.db.resource.data.CompanyResource;
import eu.onepay.db.resource.data.FinServiceImageResource;
import eu.onepay.db.resource.data.FinServicePubKeyResource;
import eu.onepay.db.resource.data.UniqueFinServiceResource;

@Slf4j
@Service
@Transactional
public class FinServicePubKeyResourceImpl extends AbstractResource implements FinServicePubKeyResource {

    @Override
    public String getPublicKey(Long id) {
        return getById(FinServicePubKey.class, id).getPublicKey();
    }
}
