package api.affiliate.api.affiliate.model.store;

import api.affiliate.api.affiliate.entity.StoreTable;
import lombok.Data;

@Data
public class StoreRequest {

    private String store;
    private String bankNameAccount;
    private String bankName;
    private String bankNumber;
    private String userId;

}


