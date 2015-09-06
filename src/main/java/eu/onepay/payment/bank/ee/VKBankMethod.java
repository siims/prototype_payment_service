package eu.onepay.payment.bank.ee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import eu.onepay.payment.MerchantCredentials;
import eu.onepay.payment.OrderCredentials;
import eu.onepay.payment.OurTransaction;
import eu.onepay.payment.PaymentCredential;

public class VKBankMethod extends BankEE {

    private VKBankPayCredentials payCrede;
    private OrderCredentials orderCrede;
    private MerchantCredentials merchCrede;
    private long id;

    public VKBankMethod ( Long id ){
        super(id);
        
    }

    @Override
    public void initAndVerify(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {

        verify(payCrede, orderCrede, merchCrede);

        init(payCrede, orderCrede, merchCrede);
    }

    private void verify(PaymentCredential payCrede, OrderCredentials orderCrede, MerchantCredentials merchCrede) {
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


    public String getVK_RETURN() {
        return payCrede.getReturnUrl();
    }

    public String getVK_CANCEL() {
        return payCrede.getCancelUrl();
    }

    public String getVK_SND_ID() {
        return payCrede.getSendersId();
    }

    public String getVK_CURR() {
        return payCrede.getCurrency();
    }

    public String getVK_CHARSET() {
        return payCrede.getEncoding();
    }

    public String getVK_VERSION() {
        return payCrede.getVersion();
    }

    public String getVK_SERVICE() {
        return payCrede.getService();
    }

    public String getVK_MAC() {
        return calcMac();
    }

    public String getVK_REF() {
        return Long.toString(orderCrede.getReferenceNo());
    }

    public String getVK_AMOUNT() {
        return Double.toString(orderCrede.getAmount());
    }

    /**
     * Max description length 95 charecters
     * 
     * @return String with max length of 95 characters
     */
    public String getVK_MSG() {
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
                getVK_SERVICE(), 
                getVK_VERSION(), 
                getVK_SND_ID(),
                getVK_STAMP(),
                getVK_AMOUNT(),
                getVK_CURR(),
                getVK_REF(),
                getVK_MSG(),
                getVK_RETURN(),
                getVK_CANCEL(),
                getVK_DATETIME(),
                }, payCrede.getPrivetKeyAlias(), true);
        
                // @formatter:on

        return mac;
    }

    public String getVK_STAMP() {
        // TODO testida korduv päring sama stampiga
        return Long.toString(getTransaction().getId());
    }

    public String getVK_DATETIME() {
        return getCurrentDate();
    }
    
    @Override
    public OurTransaction getTransaction() {
        super.getTransaction().setTimeToWait(300_000L);
        return super.getTransaction();
    }

}
