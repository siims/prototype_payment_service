package eu.onepay.payment.bank.ee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.commons.codec.binary.Base64;

public class BankEE {

    public static String ENCODING = "UTF-8";

    public static String keyLocation = "src/main/resources/truststore.ks";

    String getMac(String[] data, String alias, boolean isPrivate) {

        String mac = null;

        try {
            Signature signingEngine = getSignature(isPrivate, alias);
            String generatedMac = generateMac(data);
            byte[] bytes = generatedMac.getBytes(ENCODING);
            signingEngine.update(bytes);

            mac = new String(Base64.encodeBase64(signingEngine.sign()), ENCODING);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac;

    }

    String generateMac(String[] data) throws Exception {
        // get data
        StringBuilder b = new StringBuilder();

        for (String par : data) {
            if (par == null) {
                continue;
            }

            par = par.trim();
            b.append(getThreePlacedStringLength(par));
            b.append(par);
        }

        return b.toString();
    }

    String getThreePlacedStringLength(String string) {

        byte[] bytes = addSymbolsLeft(string, '0', 3);
        return new String(bytes);

    }

    private byte[] addSymbolsLeft(String str, char c, int len) {

        String s = Integer.toString(str.length());

        while (s.length() < len) {
            s = c + s;
        }

        CharBuffer cb = CharBuffer.wrap(s);
        ByteBuffer bb = ByteBuffer.allocate(cb.length());
        for (int i = 0; i < cb.length(); i++) {
            bb.put((byte) cb.get(i));
        }

        return bb.array();
    }

    static Signature getSignature(boolean isPrivate, String alias) throws NoSuchAlgorithmException, KeyStoreException,
            CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, InvalidKeyException {
        Signature signingEngine = Signature.getInstance("SHA1withRSA");

        String ksPassword = "123456";

        String ksLocation = keyLocation;

        KeyStore ks = KeyStore.getInstance("JKS");
        char[] passPhraseKs = ksPassword.toCharArray();
        File certificateFile = new File(ksLocation);

        ks.load(new FileInputStream(certificateFile), passPhraseKs);

        if (isPrivate) {
            PrivateKey key = (PrivateKey) ks.getKey(alias, passPhraseKs);
            if (key == null)
                return null;
            signingEngine.initSign(key);
        } else {
            PublicKey key = ks.getCertificate(alias).getPublicKey();
            if (key == null)
                return null;
            signingEngine.initVerify(key);
        }

        return signingEngine;

    }

    

}
