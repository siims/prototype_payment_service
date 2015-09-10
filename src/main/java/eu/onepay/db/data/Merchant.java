package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = Merchant.TABLE)
public class Merchant extends AbstractStatefulData {

    public static final String TABLE = "merchant";
    
    public static final String COL_COMPANY_ID = "company_id";

    public static final String COL_NAME = "normalized_name";


    public static final String FLD_COMPANY_ID = "companyId";
    
    public static final String FLD_NAME = "name";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_COMPANY_ID)
    private Integer companyId;

}
