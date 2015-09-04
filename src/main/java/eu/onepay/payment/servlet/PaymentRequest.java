package eu.onepay.payment.servlet;

import com.google.gson.annotations.SerializedName;

public class PaymentRequest {

    @SerializedName("merchant_id")
    public Long merchantId;
    @SerializedName("pay_option")
    public Long paymentId;
    @SerializedName("order_id")
    public String orderId;
    @SerializedName("amount")
    public Double amount;
    @SerializedName("reference_no")
    public Long referenceNo;
    @SerializedName("explanation")
    public String explanation;

}
