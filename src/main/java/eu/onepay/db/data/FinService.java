package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = FinService.TABLE)
public class FinService extends AbstractStatefulData {

    public static final String TABLE = "fin_service";

    public static final String COL_FIN_COMPANY = "fin_company_id";

    public static final String COL_TYPE = "fin_service_type";

    public static final String COL_NAME = "name";


    public static final String FLD_FIN_COMPANY_ID = "finCompanyId";

    public static final String FLD_NAME = "name";

    public static final String FLD_TYPE_ID = "typeId";

    @Column(name = COL_FIN_COMPANY)
    private Integer finCompanyId;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_TYPE)
    private Integer typeId;

}
