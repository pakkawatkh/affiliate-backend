package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.CustomerBusiness;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.customer.CustomerLoginRequest;
import api.affiliate.api.affiliate.model.customer.CustomerRegisterRequest;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    public final CustomerBusiness customerBusiness;

    public CustomerController(CustomerBusiness customerBusiness) {
        this.customerBusiness = customerBusiness;
    }

//    POST
    @PostMapping("/customer-register")
    public ResponseEntity<Object> register(@RequestBody CustomerRegisterRequest request) throws BaseException {
        Object register = customerBusiness.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/customer-login")
    public ResponseEntity<Object> login(@RequestBody CustomerLoginRequest request) throws BaseException {
        Object login = customerBusiness.login(request);
        return ResponseEntity.ok(login);
    }




}
