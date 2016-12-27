package eu.onepay.payment.servlet.dummy;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@Slf4j
@WebServlet("/get/dummy/merchant/*")
public class DummyMerchant extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Dummy ran");
        try {
            logic(request, response);
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void logic(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {

        String orderId = request.getParameter("order_id");
        String sum = request.getParameter("sum");

        int merchantId = 1;
        String merchantSecret = "secret";

        String toBeHashed= orderId + sum + merchantId + merchantSecret;
        Security.addProvider(new BouncyCastleProvider());

        MessageDigest mda = MessageDigest.getInstance("SHA-512", "BC");
        byte[] digesta = mda.digest(toBeHashed.getBytes());
        String resp = "jsonpCallback('"+Hex.encodeHex(digesta).toString()+ "')";
        response.getWriter().write(Hex.encodeHex(digesta));

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {

        String orderId = "2211";
        String sum = "300";

        int merchantId = 1;
        String merchantSecret = "secret";
        String hash = "57ef9d605b5ac15101c8fe221f15a9eb3f8020272538f849d52e32dd43459775f3eac8d678f0b1facf95b944a35c79f8c7b6eca5e4fce53c4bfd536c305f672f";

        String data = orderId + sum + merchantId + merchantSecret;
        Security.addProvider(new BouncyCastleProvider());

        MessageDigest mda = MessageDigest.getInstance("SHA-512", "BC");
        byte[] digesta = mda.digest(data.getBytes());

        System.out.println(Hex.encodeHex(digesta));

    }

}
