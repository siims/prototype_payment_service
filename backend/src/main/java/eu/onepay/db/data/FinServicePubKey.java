package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = FinServicePubKey.TABLE)
public class FinServicePubKey extends AbstractStatefulData {

    public static final String TABLE = "fin_service_pub_key";

    public static final String COL_PUB_KEY = "pub_key";

    public static final String COL_FIN_COMPANY_ID = "fin_company_id";


    public static final String FLD_IMAGE_URL= "publicKey";

    public static final String FLD_FIN_COMPANY_ID = "finCompanyId";

    @Column(name = COL_PUB_KEY)
    private String publicKey;

    @Column(name = COL_FIN_COMPANY_ID)
    private Long finCompanyId;
}
