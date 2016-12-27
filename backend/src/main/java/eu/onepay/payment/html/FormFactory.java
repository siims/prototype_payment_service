package eu.onepay.payment.html;

import java.util.HashMap;
import java.util.Map;

import eu.onepay.payment.PaymentSolution;
import eu.onepay.payment.bank.ee.VKBankMethod;

public class FormFactory {

    public static Form asForm(PaymentSolution payMethod) {
        Form form = null;
        if (payMethod instanceof VKBankMethod) {
            VKBankMethod vkMethod = (VKBankMethod) payMethod;
            form = vkBankAsForm(vkMethod);
        }
        return form;
    }

    private static Form vkBankAsForm(VKBankMethod payMethod) {

        Form form = new Form();
        form.setMethod("POST");
        form.setAction("http://localhost:8081/banklink/seb-common");

        Map<String, String> inputNameValue = new HashMap<>();
        inputNameValue.put("VK_AMOUNT", payMethod.getVK_AMOUNT());
        inputNameValue.put("VK_STAMP", payMethod.getVK_STAMP());
        inputNameValue.put("VK_DATETIME", payMethod.getVK_DATETIME());
        inputNameValue.put("VK_REF", payMethod.getVK_REF());
        inputNameValue.put("VK_MSG", payMethod.getVK_MSG());
        inputNameValue.put("VK_MAC", payMethod.getVK_MAC());
        inputNameValue.put("VK_SERVICE", payMethod.getVK_SERVICE());
        inputNameValue.put("VK_VERSION", payMethod.getVK_VERSION());
        inputNameValue.put("VK_CHARSET", payMethod.getVK_CHARSET());
        inputNameValue.put("VK_CURR", payMethod.getVK_CURR());
        // kliendile tuleb VK_SND_ID eraldi võtta kui tal on custom
        // lahenduse("uid100010")
        inputNameValue.put("VK_SND_ID", payMethod.getVK_SND_ID());
        inputNameValue.put("VK_CANCEL", payMethod.getVK_CANCEL());
        inputNameValue.put("VK_RETURN", payMethod.getVK_RETURN());

        inputNameValue.forEach((name, value) -> form.addInputElement(name, value));

        return form;

    }

}
