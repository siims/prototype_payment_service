package eu.onepay.db.resource.data;

import java.util.List;

import eu.onepay.db.data.PaymentMethod;



public interface PaymentMethodResource extends CRUDResource {

    public boolean deactivate(long paymentMethodId);

    public List<PaymentMethod> getMerchantPaymentMethods(long merchantId);

}
