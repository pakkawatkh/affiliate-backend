package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.business.ProductBusiness;
import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
}


@RestController
@RequestMapping("/auth")
class AuthUserController {
    public final UserBusiness userBusiness;

    AuthUserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/user-register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterRequest request) throws BaseException {
        Object register = userBusiness.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/user-login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest request) throws BaseException {
        Object login = userBusiness.login(request);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/getAll-user")
    public List<UserTable> getAllUser() throws BaseException{
        List<UserTable> user = userBusiness.findAllUser();
        return user;
    }

}


@RestController
@RequestMapping("/auth")
class AuthProductController {
    public final ProductBusiness productBusiness;

    AuthProductController(ProductBusiness productBusiness) {
        this.productBusiness = productBusiness;
    }

    @GetMapping("/getAll-product")
    public ResponseEntity<List<ProductTable>> getAllProduct(){
//        List<ProductTable> product = productBusiness.findAllProduct();
//        return product;
        List<ProductTable> product = productBusiness.findAllByStatusIsTrue();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id){
        Object product = productBusiness.findByProductById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/getAll-productByStoreId/{id}")
    public ResponseEntity<Object> getProductByStoreId(@PathVariable Integer id){
        List<ProductTable> product = productBusiness.findAllProductByStoreId(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/getAll-byStatusIsTrue-product")
    public ResponseEntity<List<ProductTable>> findAllByStatusIsTrue() {
        List<ProductTable> product = productBusiness.findAllByStatusIsTrue();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product-search")
    public ResponseEntity<Object> getProductSearch(@RequestParam(name = "keyword") String keyword) {
        List<ProductTable> product = productBusiness.getProductSearch(keyword);
        return ResponseEntity.ok(product);
    }
}

@RestController
@RequestMapping("/auth")
class AuthAdminController {

    public final AdminBusiness adminBusiness;

    public final UserBusiness userBusiness;

    AuthAdminController(AdminBusiness adminBusiness, UserBusiness userBusiness) {
        this.adminBusiness = adminBusiness;
        this.userBusiness = userBusiness;
    }

//    GET
    @GetMapping("/getAll-user-admin")
    public List<UserTable> getAllUser(){
        List<UserTable> user = userBusiness.findAllUser();
        return user;
    }
}






