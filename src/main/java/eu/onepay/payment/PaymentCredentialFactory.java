package eu.onepay.payment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.MalformedURLException;

import eu.onepay.db.data.PaymentMethod;
import eu.onepay.payment.bank.ee.VKBankPayCredential;

public class PaymentCredentialFactory {

    public static Map<Long /*paymentId*/, PaymentCredential> makePaymentCredentialMap(List<PaymentMethod> paymentMethods) {
        // TODO: Add other payment credential types
        String sendersId = "uid100010";
        String returnUrl = "http://nomme.eu/onepay.html?return";
        String cancelUrl = "http://nomme.eu/onepay.html?cancel";
        String privateKeyAlias = "1";
        String publicKey = "-----BEGIN CERTIFICATE-----\nMIIDljCCAn4CCQCBmddvYTNKbTANBgkqhkiG9w0BAQUFADCBjDELMAkGA1UEBhMCRUUxETAPBgNVBAgTCEhhcmp1bWFhMRAwDgYDVQQHEwdUYWxsaW5uMQ0wCwYDVQQKEwRUZXN0MREwDwYDVQQLEwhiYW5rbGluazEXMBUGA1UEAxMObG9jYWxob3N0IDgwODExHTAbBgkqhkiG9w0BCQEWDnRlc3RAbG9jYWxob3N0MB4XDTE1MDgzMTExNDExNVoXDTM1MDgyNjExNDExNVowgYwxCzAJBgNVBAYTAkVFMREwDwYDVQQIEwhIYXJqdW1hYTEQMA4GA1UEBxMHVGFsbGlubjENMAsGA1UEChMEVGVzdDERMA8GA1UECxMIYmFua2xpbmsxFzAVBgNVBAMTDmxvY2FsaG9zdCA4MDgxMR0wGwYJKoZIhvcNAQkBFg50ZXN0QGxvY2FsaG9zdDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBANfS/2Z5c/1PBveT0RvM/TA162oumn8P7ecG0mfM2FI7i2uAtXlluj0fMLj8Slw6xO2y6SmTLkq31XoO+hDy10gp7OhKZLhyaSS1ueFO1ef/j7gGvbipXhxPS6HToyI06NbaSel206eP+NrbF+DvZ76RY42bkAScN5Y8AGGUdQUX1OPVOgbv7LO+3sUfQNBDMwxy/y+rCI0AHYR0bKtas7h18TS6WpDnj7TZCPTJIX1jUO5pWCvPsi4t3k/yiUAEALEpn//qZhdR+lgP5n0SNo63SWTkpgT+qnJ2vrycLJTJ7I+G/5BPKtMZoKHOohILrqBPP8Bhmrx9hF4UXVlZXYcCAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAwD3Iis24RLRKO6CehOXB31Id4Pe+jz3PyFNJjC4mM7beYcpbDVRX4TMb5BfBiYgK80y417jT1k3Wsi4HwOSkU+bANiKjE0YJRLx8uNCV0XkFv2clz7saJq0OgCqs2+8hxlm6tiDoU3JowVeadCkU4gnzZlaLlviHLVd+zVdwLQM8VPm+0Y5H2stveq0Yj5t2ekvsSxoYiXD1Aga2plZBWUp4bgGVOr1uIkV8IoboVbe6A4D5QBakXAucioizighVv7zJGQ/Ir0HbP02G6AbcerVs4K3mMTkwgAlhFVjJtyAKd0QvA86XQovB9TaGWUcX/zwcOVu9G8/zjpVVxvTdfw==-----END CERTIFICATE-----";

        String defaultCancelUrl = "http://localhost:8080/onepay_bankpayment/callback/23/merchant/1";
        String defaultReturnUrl = "http://localhost:8080/onepay_bankpayment/callback/23/merchant/1";

        return paymentMethods.stream().collect(Collectors.toMap(paymentMethod -> paymentMethod.getId(), // FIXME: uniqueFinServiceId
                paymentMethod -> new VKBankPayCredential(23L, sendersId, returnUrl, cancelUrl, privateKeyAlias, defaultReturnUrl,
                        defaultCancelUrl, publicKey)));
    }

    private String veryfyUrl(String url) throws MalformedURLException {
        // TODO: ka HTTPS
        boolean startsWith = url.startsWith("http://");
        if (startsWith) {
            return url;
        } else {
            throw new MalformedURLException("URL:" + url + " but should start with: http://");
        }
    }
}
