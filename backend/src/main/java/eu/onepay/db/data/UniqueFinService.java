package eu.onepay.db.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = UniqueFinService.TABLE)
public class UniqueFinService extends AbstractStatefulData {

    public static final String TABLE = "unique_fin_service";

    public static final String COL_PRIMARY_FIN_SERVICE_ID = "other_fin_service_id";

    public static final String COL_ALTERNATIVE_FIN_SERVICE_ID = "our_alternative_fin_service_id";

    public static final String COL_VALID_TILL_DATE = "valid_till_date";


    public static final String FLD_PRIMARY_FIN_SERVICE_ID = "primaryFinServiceId";

    public static final String FLD_ALTERNATIVE_FIN_SERVICE_ID = "ourServiceId";

    public static final String FLD_VALID_TILL_DATE = "validTillDate";

    @Column(name = COL_PRIMARY_FIN_SERVICE_ID)
    private Long primaryFinServiceId;

    @Column(name = COL_ALTERNATIVE_FIN_SERVICE_ID)
    private Long ourServiceId;

    @Column(name = COL_VALID_TILL_DATE)
    private LocalDate validTillDate;
}
