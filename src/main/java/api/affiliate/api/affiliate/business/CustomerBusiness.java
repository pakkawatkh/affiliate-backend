package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import api.affiliate.api.affiliate.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomerBusiness {


    private final UserService userService;

    public CustomerBusiness(UserService userService) {
        this.userService = userService;
    }


    public Object register(UserRegisterRequest request) throws BaseException {
        request.valid();
        userService.register(request.getUserName(), request.getPassWord(), request.getFullName(), request.getEmail(), request.getTel(), request.getAddress(), request.getSub(), request.getDistrict(), request.getProvince(), request.getPostalCode());
        return new Response().success("register success");
    }
}
