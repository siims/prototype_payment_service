package eu.onepay.payment.bank.ee;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import eu.onepay.payment.PaymentCredential;

@RequiredArgsConstructor
public class VKBankPayCredential implements PaymentCredential {
    // @formatter:off
    @Getter @NonNull private Long paymentId;
    @Getter @NonNull private String imageUrl = "http://www.seb.ee/sites/default/files/web/images/logod/seb_88x31.gif";
    private String bankName;
    @Getter @NonNull private transient String sendersId;
    @Getter @NonNull private transient String returnUrl;
    @Getter @NonNull private transient String cancelUrl;
    @Getter final private transient String version = "008";
    @Getter final private transient String service = "1012";
    @Getter @NonNull private transient String currency = "EUR";
    @Getter final private transient String encoding = "UTF-8";
    @Getter @NonNull private transient String privateKeyAlias;
    @Getter @NonNull private transient String defaultReturnUrl;
    @Getter @NonNull private transient String defaultCancelUrl;
    @Getter @NonNull private transient String publicKey;
    // @formatter:on

    @Override
    public String getName() {
        return bankName;
    }
}
