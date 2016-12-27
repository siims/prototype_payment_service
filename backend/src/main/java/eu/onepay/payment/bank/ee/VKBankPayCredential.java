package eu.onepay.payment.bank.ee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import eu.onepay.payment.PaymentCredential;

@RequiredArgsConstructor
@NoArgsConstructor
public class VKBankPayCredential implements PaymentCredential {
    // @formatter:off
    @Getter @NonNull private Long uniqueFinancialServiceId;
    @Getter @NonNull private String imageUrl;
    @NonNull private String bankName;
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
