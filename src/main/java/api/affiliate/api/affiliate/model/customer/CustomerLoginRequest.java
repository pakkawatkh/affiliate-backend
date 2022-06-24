package api.affiliate.api.affiliate.model.customer;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CustomerException;
import lombok.Data;

@Data
public class CustomerLoginRequest {

    private String customerName;

    private String passWord;

    public void valid() throws BaseException {
        if (customerName == null || passWord == null) {
            throw CustomerException.customerRequestInvalid();
        }
        if (customerName.isBlank() || passWord.isBlank()) {
            throw CustomerException.customerRequestInvalid();
        }
    }
}
