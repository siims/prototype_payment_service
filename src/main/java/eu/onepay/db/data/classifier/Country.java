package eu.onepay.db.data.classifier;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Country.TABLE)
public class Country extends AbstractClassifierData {

    public static final String TABLE = "country";
}
