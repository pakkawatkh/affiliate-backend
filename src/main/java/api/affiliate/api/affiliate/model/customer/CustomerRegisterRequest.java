package api.affiliate.api.affiliate.model.customer;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import lombok.Data;

@Data
public class CustomerRegisterRequest {

    private String customerName;

    private String passWord;

    private  String fullName;

    private String email;

    private String tel;

    private String address;

    private String sub;

    private String district;

    private String province;

    private String postalCode;

    public void valid() throws BaseException {

        if (customerName == null || passWord == null) {
            throw UserException.userRequestInvalid();
        }

        if (customerName.isBlank() || passWord.isBlank()) {
            throw UserException.userRequestInvalid();
        }
    }
}
