package eu.onepay.db.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractStatefulData extends AbstractData {

    public static final String COL_ACTIVE = "active";

    public static final String COL_CREATED_DATE = "created_date";

    public static final String COL_MOD_DATE = "modified_date";

    public static final String FLD_ACTIVE = "active";

    public static final String FLD_CREATED_DATE = "createdDate";

    public static final String FLD_MOD_DATE = "modifiedDate";

    @Column(name = COL_ACTIVE)
    private Boolean active = true;

    @Column(name = COL_MOD_DATE, insertable = false)
    private LocalDate createdDate;

    @Column(name = COL_MOD_DATE, insertable = false)
    private LocalDate modifiedDate;
}
