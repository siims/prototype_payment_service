package eu.onepay.payment;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.onepay.db.data.PaymentMethod;
import eu.onepay.db.resource.data.FinServiceImageResource;
import eu.onepay.db.resource.data.FinServicePubKeyResource;
import eu.onepay.db.resource.data.MerchantCryptKeyResource;
import eu.onepay.db.resource.data.MerchantUrlResource;
import eu.onepay.db.resource.data.UniqueFinServiceResource;
import eu.onepay.payment.bank.ee.VKBankPayCredential;

@Slf4j
@Component
public class PaymentCredentialFactory {

    @Autowired private UniqueFinServiceResource uniqueFinServiceResource;

    @Autowired private FinServiceImageResource finServiceImageResource;

    @Autowired private FinServicePubKeyResource finServicePubKeyResource;

    @Autowired private MerchantCryptKeyResource merchantCryptKeyResource;

    @Autowired private MerchantUrlResource merchantUrlResource;

    public Map<Long /*paymentId*/, PaymentCredential> makePaymentCredentialMap(List<PaymentMethod> paymentMethods) {
        // TODO: Add other payment credential types
        log.error("makePaymentCredentialMap, num payment_methods: {}", paymentMethods.size());
        if (paymentMethods.size() == 0) {
            return new HashMap<Long /*paymentId*/, PaymentCredential>();
        }

        return paymentMethods.stream().collect(Collectors
                .toMap(
                        paymentMethod -> uniqueFinServiceResource.getId(paymentMethod.getFinServiceId()).getId(),
                        paymentMethod -> {
                            try {
                                Long uniqueFinServiceId = uniqueFinServiceResource.getId(paymentMethod.getFinServiceId())
                                        .getId();
                                String imageUrl = finServiceImageResource.getUrl(paymentMethod.getImageId());
                                String bankName = "FOO"; // FIXME: get name from db
                                String sendersId = paymentMethod.getSenderId();
                                String returnUrl = veryfyUrl(merchantUrlResource.getUrl(paymentMethod.getSuccessUrlId()));
                                String cancelUrl = veryfyUrl(merchantUrlResource.getUrl(paymentMethod.getFailureUrlId()));
                                String currency = "EUR";
                                String privateKeyAlias = merchantCryptKeyResource.getKeyAlias(paymentMethod
                                        .getMerchantKeyId());
                                String publicKey = finServicePubKeyResource.getPublicKey(paymentMethod.getPublicKeyId());
    
                                String defaultCancelUrl = veryfyUrl(String.format(
                                        "http://localhost:8080/onepay_bankpayment/callback/%d/merchant/%d",
                                        uniqueFinServiceId, paymentMethod.getMerchantId()));
                                String defaultReturnUrl = veryfyUrl(defaultCancelUrl);
                                return new VKBankPayCredential(uniqueFinServiceId, imageUrl, sendersId,
                                        returnUrl, cancelUrl,
                                        currency, privateKeyAlias,
                                        defaultReturnUrl, defaultCancelUrl, publicKey);
                            } catch (MalformedURLException e) {
                                log.error("PaymentMethod with id {} not accepted - given url not valid.", 
                                        paymentMethod. getId(), e);
                                return new VKBankPayCredential();
                            }
                        }));
    }

    private String veryfyUrl(String url) throws MalformedURLException {
        boolean startsWith = url.startsWith("http://");
        if (!startsWith) {
            startsWith = url.startsWith("https://");
        }

        if (startsWith) {
            return url;
        } else {
            throw new MalformedURLException("URL:" + url + " but should start with: http:// or https://");
        }
    }
}
