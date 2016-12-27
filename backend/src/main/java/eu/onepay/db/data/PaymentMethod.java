package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = PaymentMethod.TABLE)
public class PaymentMethod extends AbstractStatefulData {

    public static final String TABLE = "payment_method";

    public static final String COL_FIN_SERVICE = "fin_service_id";

    public static final String COL_MERCHANT = "merchant_id";

    public static final String COL_BANK_ACCOUNT = "receiving_bank_account_id";

    public static final String COL_FIN_SERVICE_PUB_KEY_ID = "fin_service_pub_key_id";

    public static final String COL_MERCHANT_KEY_ID = "merchant_crypt_key_id";

    public static final String COL_SENDER_ID_IN_FIN_SERVICE = "sender_id_in_fin_service";

    public static final String COL_SUCCESS_URL_ID = "success_url_id";

    public static final String COL_FAILURE_URL_ID = "failure_url_id";

    public static final String COL_FIN_SERVICE_IMAGE_ID = "fin_service_image_id";

    // TODO: add many other fields

    public static final String FLD_FIN_SERVICE_ID = "finServiceId";

    public static final String FLD_MERCHANT_ID = "merchantId";

    public static final String FLD_BANK_ACCOUNT_ID = "bankAccountId";

    @Column(name = COL_FIN_SERVICE)
    private Long finServiceId;

    @Column(name = COL_MERCHANT)
    private Long merchantId;

    @Column(name = COL_BANK_ACCOUNT)
    private Long bankAccountId;
    
    @Column(name = COL_FIN_SERVICE_PUB_KEY_ID)
    private Long publicKeyId;
    
    @Column(name = COL_MERCHANT_KEY_ID)
    private Long merchantKeyId;
    
    @Column(name = COL_SENDER_ID_IN_FIN_SERVICE)
    private String senderId;
    
    @Column(name = COL_SUCCESS_URL_ID)
    private Long successUrlId;
    
    @Column(name = COL_FAILURE_URL_ID)
    private Long failureUrlId;
    
    @Column(name = COL_FIN_SERVICE_IMAGE_ID)
    private Long imageId;
}
