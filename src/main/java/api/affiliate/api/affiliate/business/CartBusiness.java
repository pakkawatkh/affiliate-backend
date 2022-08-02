package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.CartItemTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.service.*;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartBusiness {

    private  final CartService cartService;
    private final UserService userService;

    private final StoreService storeService;
    private final ProductService productService;
    private final TokenService tokenService;

    private final FileService fileService;

    public CartBusiness(CartService cartService, TokenService tokenService, UserService userService, StoreService storeService, ProductService productService, FileService fileService) {
        this.cartService = cartService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.storeService = storeService;
        this.productService = productService;
        this.fileService = fileService;
    }

    public List<CartItemTable> getMyCart() throws BaseException{
        UserTable user = tokenService.getUserByToken();
        List<CartItemTable> cart = cartService.listCartItem(user.getUserId());
        return cart;
    }


    public Object addProduct(Integer productId) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER " + user);
        ProductTable product = productService.findByProductId(productId);
        System.out.println("PRODUCT " + product);
        cartService.addProduct(user.getUserId(), product.getProductId());
        return new Response().success("add product success");
    }

    public Object updateCart(Integer productId) throws BaseException{
        UserTable user = tokenService.getUserByToken();
        ProductTable product = productService.findByProductId(productId);
        CartItemTable request = cartService.findByUserId(user.getUserId());
        System.out.println(request);
        cartService.updateCart(request, request.getCartId(), product.getProductId());
        return new Response().success("update product success");
    }




}