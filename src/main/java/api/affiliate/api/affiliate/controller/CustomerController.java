package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.CustomerBusiness;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    public final CustomerBusiness customerBusiness;

    public CustomerController(CustomerBusiness customerBusiness) {
        this.customerBusiness = customerBusiness;
    }

////    POST
//    @PostMapping("customer-register")
//    public ResponseEntity<Object> register(@RequestBody UserRegisterRequest request) {
//        Object register = customerBusiness.register(request);
//        return ResponseEntity.ok(register);
//    }
}
