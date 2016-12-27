package eu.onepay.db.resource.data;

import java.util.List;
import java.util.Set;

import eu.onepay.db.data.Merchant;

public interface MerchantResource extends CRUDResource {

    public List<Merchant> listActive();

    public Long getMerchantId(long companyId, String name);

    public List<Long> getMerchantIds(long companyId);

    public boolean deactivateMerchant(long merchantId);

    public long storeMerchant(Merchant merchant);
}
