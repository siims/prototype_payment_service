package eu.onepay.db.resource.data;

import java.util.List;
import java.util.Set;

import eu.onepay.db.data.Merchant;

public interface MerchantResource extends CRUDResource {

    public List<Merchant> listActive();

    public Integer getMerchantId(int companyId, String name);

    public List<Integer> getMerchantIds(int companyId);

    public boolean deactivateMerchant(int merchantId);

    public int storeMerchant(Merchant merchant);
}
