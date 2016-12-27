package eu.onepay.db.data.classifier;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import eu.onepay.db.data.AbstractData;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractClassifierData extends AbstractData {

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_MANUAL = "manual";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column(name = COL_MANUAL)
    private Boolean manual;

    public AbstractClassifierData() {}

    public AbstractClassifierData(Long id) {
        super(id);
    }
}
