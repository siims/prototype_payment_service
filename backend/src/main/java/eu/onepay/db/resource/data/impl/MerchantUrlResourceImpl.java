package eu.onepay.db.resource.data.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.MerchantUrl;
import eu.onepay.db.resource.data.MerchantUrlResource;

@Slf4j
@Service
@Transactional
public class MerchantUrlResourceImpl extends AbstractResource implements MerchantUrlResource {

    @Override
    public String getUrl(Long id) {
        return getById(MerchantUrl.class, id).getUrl();
    }
}
