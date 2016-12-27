package eu.onepay.db.data.classifier;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = FeeType.TABLE)
public class FeeType extends AbstractClassifierData {

    public static final String TABLE = "fee_type";
}
