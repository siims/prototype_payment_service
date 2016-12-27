package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = MerchantCryptKey.TABLE)
public class MerchantCryptKey extends AbstractStatefulData {

    public static final String TABLE = "merchant_crypt_key";

    public static final String COL_KEY_ALIAS = "key_alias";

    public static final String COL_MERCHANT_ID = "merchant_id";


    public static final String FLD_KEY_ALIAS= "keyAlias";

    public static final String FLD_NAME = "name";

    public static final String FLD_COUNTRY_ID = "countryId";

    @Column(name = COL_KEY_ALIAS)
    private String keyAlias;

    @Column(name = COL_MERCHANT_ID)
    private Long merchantId;
}
