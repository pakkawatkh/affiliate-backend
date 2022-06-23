package api.affiliate.api.affiliate.model.store;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.StoreException;

import lombok.Data;

@Data
public class StoreRegisterRequest {

    private String store;

    private String bankNameAccount;

    private String bankName;

    private  String bankNumber;

    public void valid() throws BaseException {
        if (store == null) {
            throw StoreException.storeRequestInvalid();
        }

        if (store.isBlank()) {
            throw StoreException.storeRequestInvalid();
        }
    }


}
