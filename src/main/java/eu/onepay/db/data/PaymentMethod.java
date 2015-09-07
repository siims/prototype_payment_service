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

    // TODO: add many other fields


    public static final String FLD_FIN_SERVICE_ID = "finServiceId";

    public static final String FLD_MERCHANT_ID = "merchantId";

    public static final String FLD_BANK_ACCOUNT_ID = "bankAccountId";

    @Column(name = COL_FIN_SERVICE)
    private Integer finServiceId;

    @Column(name = COL_MERCHANT)
    private Integer merchantId;

    @Column(name = COL_BANK_ACCOUNT)
    private Integer bankAccountId;
}
