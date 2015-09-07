package eu.onepay.db.data.classifier;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ContactType.TABLE)
public class ContactType extends AbstractClassifierData {

    public static final String TABLE = "contact_type";
}
