package eu.onepay.payment.bank.ee.calllback;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
        System.out.println(key);
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
        System.out.println("Mymac:" + BankEE.generateMac(params));
        return BankEE.generateMac(params);
    }

    private String getVK_SND_NAME() throws UnsupportedEncodingException {
       return req.getParameter("VK_SND_NAME");
    }

    private String getVK_SND_ACC() throws UnsupportedEncodingException {
       return req.getParameter("VK_SND_ACC");
    }

    private String getVK_REC_NAME() throws UnsupportedEncodingException {
       return req.getParameter("VK_REC_NAME");
    }

    private String getVK_REC_ACC() throws UnsupportedEncodingException {
       return req.getParameter("VK_REC_ACC");
    }

    private String getVK_T_NO() throws UnsupportedEncodingException {
       return req.getParameter("VK_T_NO");
    }

    private String getVK_REC_ID() throws UnsupportedEncodingException {
       return req.getParameter("VK_REC_ID");
    }

    private String getVK_MSG() throws UnsupportedEncodingException {
       return req.getParameter("VK_MSG");
    }

    private String getVK_REF() throws UnsupportedEncodingException {
        return req.getParameter("VK_REF");
    }

    private String getVK_AMOUNT() throws UnsupportedEncodingException {
       return req.getParameter("VK_AMOUNT");
    }

    public String getVK_RETURN() throws UnsupportedEncodingException {
       return req.getParameter("VK_RETURN");
    }

    public String getVK_CANCEL() throws UnsupportedEncodingException {
       return req.getParameter("VK_CANCEL");
    }

    public String getVK_SND_ID() throws UnsupportedEncodingException {
       return req.getParameter("VK_SND_ID");
    }

    public String getVK_CURR() throws UnsupportedEncodingException {
       return req.getParameter("VK_CURR");
    }

    public String getVK_CHARSET() throws UnsupportedEncodingException {
       return req.getParameter("VK_CHARSET");
    }

    public String getVK_VERSION() throws UnsupportedEncodingException {
       return req.getParameter("VK_VERSION");
    }

    public String getVK_SERVICE() throws UnsupportedEncodingException {
       return req.getParameter("VK_SERVICE");
    }

    public String getVK_MAC() throws UnsupportedEncodingException {
       return req.getParameter("VK_MAC");
    }

    public String getVK_STAMP() throws UnsupportedEncodingException {
       return req.getParameter("VK_STAMP");
    }

    public String getVK_T_DATETIME() throws UnsupportedEncodingException {
        return req.getParameter("VK_T_DATETIME");
    }

}
