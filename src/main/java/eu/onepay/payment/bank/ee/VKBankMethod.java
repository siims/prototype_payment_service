package eu.onepay.payment.bank.ee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.PayMethod;
import eu.onepay.payment.PaymentCredential;
import eu.onepay.payment.html.Form;

public class VKBankMethod extends BankEE implements PayMethod {

    private VKBankPayCredentials payCrede;
    private OrderCredentials orderCrede;
    private MerchantCredentials merchCrede;
    private long id;
    private boolean valid;

    public VKBankMethod ( Long id ){
        this.id = id;
    }

    @Override
    public void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {

        verify(payCrede, orderCrede, merchCrede);

        init(payCrede, orderCrede, merchCrede);
    }

    private void verify(PaymentCredential payCrede2, OrderCredentials orderCrede2, MerchantCredentials merchCrede2) {
        // TODO: maybe should make deeper verification, check if all fields are
        // here that are needed
        if (payCrede instanceof VKBankPayCredentials) {
            valid = true;
        } else {
            valid = false;
        }
    }

    private void init(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {
        VKBankPayCredentials vkPayCrede = null;

        if (payCrede instanceof VKBankPayCredentials) {
            vkPayCrede = (VKBankPayCredentials) payCrede;
        }

        this.payCrede = vkPayCrede;
        this.orderCrede = orderCrede;
        this.merchCrede = merchCrede;
        this.id = payCrede.getPaymentId();
    }

    @Override
    public Form asForm() {
        Form form = new Form();
        form.setMethod("POST");
        form.setAction("http://localhost:8081/banklink/seb-common");

        Map<String, String> inputNameValue = new HashMap<>();
        inputNameValue.put("VK_AMOUNT", Double.toString(orderCrede.getAmount()));
        inputNameValue.put("VK_STAMP", getVk_stamp());
        inputNameValue.put("VK_DATETIME", getCurrentDate());
        inputNameValue.put("VK_REF", Long.toString(orderCrede.getReferenceNo()));
        inputNameValue.put("VK_MSG", getDescription());
        inputNameValue.put("VK_MAC", calcMac());
        inputNameValue.put("VK_SERVICE", payCrede.getService());
        inputNameValue.put("VK_VERSION", payCrede.getVersion());
        inputNameValue.put("VK_CHARSET", payCrede.getEncoding());
        inputNameValue.put("VK_CURR", payCrede.getCurrency());
        // kliendile tuleb VK_SND_ID eraldi võtta kui tal on custom
        // lahenduse("uid100010")
        inputNameValue.put("VK_SND_ID", payCrede.getSendersId());
        inputNameValue.put("VK_CANCEL", payCrede.getCancelUrl());
        inputNameValue.put("VK_RETURN", payCrede.getReturnUrl());

        inputNameValue.forEach((name, value) -> form.addInputElement(name, value));

        return form;
    }

    /**
     * Max description length 95 charecters
     * 
     * @return String with max length of 95 characters
     */
    private String getDescription() {
        String retString = null;

        retString = merchCrede.getMerchantId() + ":" + merchCrede.getName() + ": " + orderCrede.getId()
                + orderCrede.getDescription();

        return StringUtils.substring(retString, 0, 95);
    }

    @Override
    public long getId() {
        return id;
    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = isoFormat.format(date);
        return format;
    }

    public boolean isValid() {
        return valid;
    }

    private String calcMac() {

        String mac = getMac(new String[] {
                // @formatter:off
                payCrede.getService(), 
                payCrede.getVersion(), 
                payCrede.getSendersId(),
                getVk_stamp(),
                Double.toString(orderCrede.getAmount()),
                payCrede.getCurrency(),
                Long.toString(orderCrede.getReferenceNo()),
                getDescription(),
                payCrede.getReturnUrl(),
                payCrede.getCancelUrl(),
                getCurrentDate(),
                }, payCrede.getPrivetKeyAlias(), true);
        
                // @formatter:on

        return mac;
    }

    private String getVk_stamp() {
        // TODO testida korduv päring sama stampiga
        return orderCrede.getId();
    }

}
