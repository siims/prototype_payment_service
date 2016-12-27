package eu.onepay.db.resource.data.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.MerchantCryptKey;
import eu.onepay.db.resource.data.MerchantCryptKeyResource;

@Slf4j
@Service
@Transactional
public class MerchantCryptKeyResourceImpl extends AbstractResource implements MerchantCryptKeyResource {

    @Override
    public String getKeyAlias(Long id) {
        return getById(MerchantCryptKey.class, id).getKeyAlias();
    }
}
