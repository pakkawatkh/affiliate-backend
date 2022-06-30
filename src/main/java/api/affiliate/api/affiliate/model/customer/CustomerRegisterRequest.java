package api.affiliate.api.affiliate.model.customer;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import lombok.Data;

@Data
public class CustomerRegisterRequest {

    private String bankNameAccount;

    private String bankName;

    private  String bankNumber;

    public void valid() throws BaseException {

//        if (customerName == null || passWord == null) {
//            throw UserException.userRequestInvalid();
//        }
//
//        if (customerName.isBlank() || passWord.isBlank()) {
//            throw UserException.userRequestInvalid();
//        }
    }
}
