package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = Company.TABLE)
public class Company extends AbstractStatefulData {

    public static final String TABLE = "company";

    public static final String COL_REG_CODE = "registration_number";

    public static final String COL_COUNTRY = "country_id";

    public static final String COL_NAME = "name_from_register";


    public static final String FLD_REG_CODE = "registrationCode";

    public static final String FLD_NAME = "name";

    public static final String FLD_COUNTRY_ID = "countryId";

    @Column(name = COL_REG_CODE)
    private String registrationCode;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_COUNTRY)
    private Long countryId;

    public Company() {}

    public Company(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
