package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.CartBusiness;
import api.affiliate.api.affiliate.entity.CartItemTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.service.CartService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    public final CartBusiness cartBusiness;

    public final UserService userService;

    public final TokenService tokenService;

    public final CartService cartService;

    public CartController(CartBusiness cartBusiness, UserService userService, TokenService tokenService, CartService cartService) {
        this.cartBusiness = cartBusiness;
        this.userService = userService;
        this.tokenService = tokenService;
        this.cartService = cartService;
    }


//        GET
    @GetMapping("/getMyCart")
    public ResponseEntity<Object> getMyCart()throws BaseException{
        Object cart = cartBusiness.getMyCart();
        return ResponseEntity.ok(cart);
    }




    @PostMapping("/add-product/{id}")
    public ResponseEntity<Object> addProduct(@PathVariable Integer id)throws BaseException{
        Object cartItem = cartBusiness.addProduct(id);
        return ResponseEntity.ok(cartItem);
    }


    @PutMapping("/update-cart/{id}")
    public ResponseEntity<Object> updateCart(@PathVariable Integer id)throws BaseException{
        Object cartItem = cartBusiness.updateCart(id);
        return ResponseEntity.ok(cartItem);
    }








}
