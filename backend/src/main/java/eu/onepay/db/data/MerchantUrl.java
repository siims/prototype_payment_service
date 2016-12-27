package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = MerchantUrl.TABLE)
public class MerchantUrl extends AbstractStatefulData {

    public static final String TABLE = "merchant_url";

    public static final String COL_URL = "url";

    public static final String COL_MERCHANT_ID = "merchant_id";

    public static final String COL_DESCRIPTION = "description";


    public static final String FLD_URL = "url";

    public static final String FLD_MERCHANT_ID= "merchantId";

    public static final String FLD_DESCRIPTION = "description";

    @Column(name = COL_URL)
    private String url;

    @Column(name = COL_MERCHANT_ID)
    private Long merchantId;

    @Column(name = COL_DESCRIPTION)
    private String description;
}
