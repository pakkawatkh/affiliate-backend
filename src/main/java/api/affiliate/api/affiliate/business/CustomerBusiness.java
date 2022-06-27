package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.CustomerTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CustomerException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.customer.CustomerLoginRequest;
import api.affiliate.api.affiliate.model.customer.CustomerRegisterRequest;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.service.CustomerService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class CustomerBusiness {


    private final TokenService tokenService;
    public final CustomerService customerService;

    public CustomerBusiness(TokenService tokenService, CustomerService customerService) {
        this.tokenService = tokenService;
        this.customerService = customerService;
    }


    public Object register(CustomerRegisterRequest request) throws BaseException {
        request.valid();
        customerService.register(request.getCustomerName(), request.getPassWord(), request.getFullName(), request.getEmail(),
                request.getTel(), request.getAddress(), request.getSub(), request.getDistrict(), request.getProvince(), request.getPostalCode());
        return new Response().success("register success");
    }




    public Object login(CustomerLoginRequest request) throws BaseException {
        request.valid();
        CustomerTable customer = customerService.findByCustomerName(request.getCustomerName());
        if (!customerService.matchPassword(request.getPassWord(), customer.getPassWord())) {
            throw CustomerException.passWordInvalid();
        }
        String tokenize = tokenService.tokenizeCustomer(customer);
        return new Response().ok(customer.getRole().toString(),"token",tokenize);
    }




    public Object getProfile() throws  BaseException {
        CustomerTable customer = tokenService.getCustomerByToken();
        return new Response().ok("","profile", customer);
    }
}
