package eu.onepay.payment.bank.ee.calllback;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import eu.onepay.payment.bank.ee.BankEE;
import eu.onepay.payment.bank.ee.VKBankPayCredentials;

public class VKBankCallback {

    private HttpServletRequest req;
    private VKBankPayCredentials payCrede;

    public VKBankCallback ( HttpServletRequest request, VKBankPayCredentials payCrede ){
        req = request;
        this.payCrede = payCrede;

    }

    public PublicKey getKey(String key) throws IOException, CertificateException {
        InputStream inStream = null;
        try {
            inStream = IOUtils.toInputStream(key);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
            return cert.getPublicKey();
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
    }

    public boolean isValid() {

        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            PublicKey publicKey = getKey(getPublicKey());
            sign.initVerify(publicKey);
            String s = getSignedText();
            sign.update(s.getBytes("UTF-8"));
            return sign.verify(Base64.decodeBase64(getVK_MAC().getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private String getPublicKey() {
        return payCrede.getPublicKey();
    }

    private String getSignedText() throws Exception {
        String[] params = new String[] {
                // @formatter:off
                getVK_SERVICE(), 
                getVK_VERSION(), 
                getVK_SND_ID(),
                getVK_REC_ID(),
                getVK_STAMP(),
                getVK_T_NO(),
                getVK_AMOUNT(),
                getVK_CURR(),
                getVK_REC_ACC(),
                getVK_REC_NAME(),
                getVK_SND_ACC(),
                getVK_SND_NAME(),
                getVK_REF(),
                getVK_MSG(),
                getVK_T_DATETIME()
                };
        return BankEE.generateMac(params);
    }

    private String getVK_SND_NAME()  {
       return req.getParameter("VK_SND_NAME");
    }

    private String getVK_SND_ACC()  {
       return req.getParameter("VK_SND_ACC");
    }

    private String getVK_REC_NAME()  {
       return req.getParameter("VK_REC_NAME");
    }

    private String getVK_REC_ACC()  {
       return req.getParameter("VK_REC_ACC");
    }

    private String getVK_T_NO()  {
       return req.getParameter("VK_T_NO");
    }

    private String getVK_REC_ID()  {
       return req.getParameter("VK_REC_ID");
    }

    private String getVK_MSG()  {
       return req.getParameter("VK_MSG");
    }

    private String getVK_REF()  {
        return req.getParameter("VK_REF");
    }

    private String getVK_AMOUNT()  {
       return req.getParameter("VK_AMOUNT");
    }

    public String getVK_RETURN()  {
       return req.getParameter("VK_RETURN");
    }

    public String getVK_CANCEL()  {
       return req.getParameter("VK_CANCEL");
    }

    public String getVK_SND_ID()  {
       return req.getParameter("VK_SND_ID");
    }

    public String getVK_CURR()  {
       return req.getParameter("VK_CURR");
    }

    public String getVK_CHARSET()  {
       return req.getParameter("VK_CHARSET");
    }

    public String getVK_VERSION()  {
       return req.getParameter("VK_VERSION");
    }

    public String getVK_SERVICE()  {
       return req.getParameter("VK_SERVICE");
    }

    public String getVK_MAC()  {
       return req.getParameter("VK_MAC");
    }

    public String getVK_STAMP()  {
       return req.getParameter("VK_STAMP");
    }

    public String getVK_T_DATETIME()  {
        return req.getParameter("VK_T_DATETIME");
    }
    public boolean isSuccessful() {
        return "1012".equals(getVK_SERVICE());
    }
    public boolean isUnuccessful() {
        return "1911".equals(getVK_SERVICE());
    }

}
