package api.affiliate.api.affiliate.model.user;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import lombok.Data;

@Data
public class UserLoginRequest {

    private String userName;

    private String passWord;

    public void valid() throws BaseException {
        if (userName == null || passWord == null) {
            throw UserException.userRequestInvalid();
        }
        if (userName.isBlank() || passWord.isBlank()) {
            throw UserException.userRequestInvalid();
        }
    }
}
