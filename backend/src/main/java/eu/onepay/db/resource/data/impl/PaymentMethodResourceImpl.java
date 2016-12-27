package eu.onepay.db.resource.data.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.onepay.db.data.PaymentMethod;
import eu.onepay.db.resource.data.PaymentMethodResource;

@Slf4j
@Service
@Transactional
public class PaymentMethodResourceImpl extends AbstractResource implements PaymentMethodResource {

    @Override
    public boolean deactivate(long paymentMethodId) {
        PaymentMethod obj = getById(PaymentMethod.class, paymentMethodId);
        obj.setActive(false);
        session().update(obj);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PaymentMethod> getMerchantPaymentMethods(long merchantId) {
        return session().createCriteria(PaymentMethod.class)
                .add(Restrictions.eq(PaymentMethod.FLD_MERCHANT_ID, merchantId))
                .add(Restrictions.eq(PaymentMethod.FLD_ACTIVE, true)).list();
    }
}
