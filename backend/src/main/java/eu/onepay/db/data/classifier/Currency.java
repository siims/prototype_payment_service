package eu.onepay.db.data.classifier;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Currency.TABLE)
public class Currency extends AbstractClassifierData {

    public static final String TABLE = "currency";
}
