package eu.onepay.db.data.classifier;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = FinServiceType.TABLE)
public class FinServiceType extends AbstractClassifierData {

    public static final String TABLE = "fin_service_type";
}
