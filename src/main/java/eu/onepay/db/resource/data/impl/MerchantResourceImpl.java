package eu.onepay.db.resource.data.impl;

import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.AbstractData;
import eu.onepay.db.data.AbstractStatefulData;
import eu.onepay.db.data.Merchant;
import eu.onepay.db.resource.data.MerchantResource;

@Slf4j
@Service
@Transactional
public class MerchantResourceImpl extends AbstractResource implements MerchantResource {

    @SuppressWarnings("unchecked")
    @Override
    public List<Merchant> listActive() {
        List<Merchant> merchants = session().createCriteria(Merchant.class)
            .add(Restrictions.eq(Merchant.FLD_ACTIVE, true)).list();
        return merchants;
    }

    @Override
    public Long getMerchantId(long companyId, String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Long> getMerchantIds(long companyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deactivateMerchant(long merchantId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long storeMerchant(Merchant merchant) {
        // TODO Auto-generated method stub
        return 0;
    }

}
