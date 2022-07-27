package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.ProductBusiness;
import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import api.affiliate.api.affiliate.service.CartService;
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
    public List<UserTable> getAllUser() {
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
    public List<ProductTable> getAllProduct() throws ProductException {
        List<ProductTable> product = productBusiness.finAllProduct();
        return product;
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) throws BaseException{
        Object product = productBusiness.findByProductById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/getAll-productByStoreId/{id}")
    public ResponseEntity<Object> getProductByStoreId(@PathVariable Integer id) throws BaseException {
        List<ProductTable> product = productBusiness.findAllProductByStoreId(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/getAll-byStatusIsTrue-product")
    public ResponseEntity<List<ProductTable>> findAllByStatusIsTrue() {
        List<ProductTable> product = productBusiness.findAllByStatusIsTrue();
        return ResponseEntity.ok(product);
    }
}
//
//@RestController
//@RequestMapping("/auth")
//class AuthCartController {
//
//    public final CartService cartService;
//
//    AuthCartController(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//
//    @PostMapping("/addProduct/{id}/{amount}")
//    public Integer addProduct(@PathVariable("id") Integer productId,
//                              @PathVariable("amount") Integer amount )throws BaseException{
//        Integer addAmount =  cartService.addProduct(productId, amount);
//        return (addAmount);
//    }

//}
