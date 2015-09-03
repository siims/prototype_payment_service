package eu.pank.payment.bank.ee;

import java.text.DecimalFormat;
import java.util.Locale;

public class VKBank extends BankEE {

    static DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.ENGLISH);
    static {
        df.applyPattern("############0.00");
    }

    private String vkService;
    private String vkVersion;
    private String vkSndId;

    private String vkMac;

    private String vkReturn;
    private String vkLang;

    private String vkStamp;
    private String vkAmount;
    private String vkCurr;
    private String vkRef;
    private String vkMsg;

    public VKBank ( String name, boolean halfAmount ){
        setName(name);
        setHalfAmount(halfAmount);
    }

    private void setHalfAmount(boolean halfAmount) {
        // TODO Auto-generated method stub

    }

    private void setName(String name) {
        // TODO Auto-generated method stub

    }

    private String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    public void init(double amount, int orderId, int referenceNo, String returnUrl, String sellerName) {

        setUrl("")/*msg.get("param.banks.vk" + getName() + ".formaction")*/;
        setButtonText("")/*msg.get("param.banks.vk" + getName() + ".button")*/;
        setButtonImage("")/*/*msg.get("param.banks.vk" + getName() + ".image")*/;
        setEncoding("")/*msg.get("param.banks.vk" + getName() + ".encoding")*/;

        vkService = ""/*"param.banks.vk" + getName() + ".service"*/;
        vkVersion = ""/*"param.banks.vk" + getName() + ".version"*/;
        vkSndId = ""/*"param.banks.vk" + getName() + ".snd_id"*/;

        vkLang = ""/*"param.banks.vk" + getName() + ".lang"*/;
        vkCurr = ""/*"param.banks.vk" + getName() + ".curr"*/;

        setVK_RETURN(returnUrl);

        setVK_AMOUNT(df.format(amount));
        setVK_STAMP(Integer.toString(orderId));
        setVK_REF(Integer.toString(referenceNo));

        setVK_MSG(sellerName + " " + orderId);
        setAlias();

        initMac();

    }

    private void setAlias() {
        // TODO Auto-generated method stub

    }

    private void setEncoding(String string) {
        // TODO Auto-generated method stub

    }

    private void setButtonImage(String string) {
        // TODO Auto-generated method stub

    }

    private void setButtonText(String string) {
        // TODO Auto-generated method stub

    }

    private void setUrl(String string) {
        // TODO Auto-generated method stub

    }

    private void initMac() {

        vkMac = getMac(new String[] { getVK_SERVICE(), getVK_VERSION(), getVK_SND_ID(), getVK_STAMP(), getVK_AMOUNT(),
                getVK_CURR(), getVK_REF(), getVK_MSG() }, getAlias(), true);

    }

    private String getAlias() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the vk_service
     */
    public String getVK_SERVICE() {
        return vkService;
    }

    /**
     * @return the vk_version
     */
    public String getVK_VERSION() {
        return vkVersion;
    }

    /**
     * @return the vk_snd_id
     */
    public String getVK_SND_ID() {
        return vkSndId;
    }

    public String getVK_MAC() {
        return vkMac;
    }

    /**
     * @return the vk_return
     */
    public String getVK_RETURN() {
        return vkReturn;
    }

    /**
     * @param vk_return
     *            the vk_return to set
     */
    public void setVK_RETURN(String vk_return) {
        this.vkReturn = vk_return;
    }

    /**
     * @return the vk_lang
     */
    public String getVK_LANG() {
        return vkLang;
    }

    /**
     * @return the vk_stamp
     */
    public String getVK_STAMP() {
        return vkStamp;
    }

    /**
     * @param vk_stamp
     *            the vk_stamp to set
     */
    public void setVK_STAMP(String vk_stamp) {
        this.vkStamp = vk_stamp;
    }

    public void setVK_REF(String vk_ref) {
        this.vkRef = vk_ref;
    }

    /**
     * @return the vk_amount
     */
    public String getVK_AMOUNT() {
        return vkAmount;
    }

    /**
     * @param vk_amount
     *            the vk_amount to set
     */
    public void setVK_AMOUNT(String vk_amount) {
        this.vkAmount = vk_amount;
    }

    /**
     * @return the vk_curr
     */
    public String getVK_CURR() {
        return vkCurr;
    }

    /**
     * @return the vk_ref
     */
    public String getVK_REF() {
        return vkRef;
    }

    /**
     * @return the vk_msg
     */
    public String getVK_MSG() {
        return vkMsg;
    }

    /**
     * @param vk_msg
     *            the vk_msg to set
     */
    public void setVK_MSG(String vk_msg) {
        this.vkMsg = vk_msg;
    }

    public String getVK_CHARSET() {
        return ENCODING;
    }

}
