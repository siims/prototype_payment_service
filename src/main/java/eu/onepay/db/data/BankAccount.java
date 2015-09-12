package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = BankAccount.TABLE)
public class BankAccount extends AbstractStatefulData {

    public static final String TABLE = "bank_account";

    public static final String COL_ACCOUNT_NUMBER = "account_number";

    public static final String COL_COMPANY = "company_id";

    public static final String COL_CURRENCY_ID = "currency_id";
    
    public static final String COL_FIN_COMPANY_ID = "fin_company_id";


    public static final String FLD_ACCOUNT_NUMBER = "accountNumber";

    public static final String FLD_CURRENCY_ID = "currencyId";

    public static final String FLD_COMPANY_ID = "companyId";
    
    public static final String FLD_FIN_COMPANY_ID = "finCompanyId";

    @Column(name = COL_ACCOUNT_NUMBER)
    private String accountNumber;

    @Column(name = COL_CURRENCY_ID)
    private Long currencyId;

    @Column(name = COL_COMPANY)
    private Long companyId;
    
    @Column(name = COL_FIN_COMPANY_ID)
    private Long finCompanyId;
}
