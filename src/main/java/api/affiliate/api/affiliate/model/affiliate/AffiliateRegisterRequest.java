package api.affiliate.api.affiliate.model.affiliate;

import api.affiliate.api.affiliate.exception.BaseException;
import lombok.Data;

@Data
public class AffiliateRegisterRequest {

    private String bankNameAccount;

    private String bankName;

    private  String bankNumber;

}
