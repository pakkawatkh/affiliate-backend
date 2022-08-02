package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.CartItemTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CartException;
import api.affiliate.api.affiliate.repository.CartItemRepository;
import api.affiliate.api.affiliate.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    public final CartItemRepository cartItemRepository;

    public final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }


    public List<CartItemTable> listCartItem(String userId) {
        List<CartItemTable> cart = cartItemRepository.findAllByUserId(userId);
        return cart;
    }


    public CartItemTable findByUserId(String userId){
        Optional<CartItemTable> cart = cartItemRepository.findByUserId(userId);
        return cart.get();
    }


    public CartItemTable findByCartId(Integer cartId){
        Optional<CartItemTable> cart = cartItemRepository.findByCartId(cartId);
        return cart.get();
    }


//    public CartItemTable findByProductId(Integer productId){
//        Optional<CartItemTable> cart = cartItemRepository.findByProductId(productId);
//        return cart.get();
//    }

    public CartItemTable findByProductId(Integer productId)throws  BaseException{
        Optional<CartItemTable> cart = cartItemRepository.findByProductId(productId);
        try {
        System.out.println("PRODUCT ID " + cart );
        }catch (Exception e){
            throw  CartException.cartRequestInvalid();
        }
        return cart.get();
    }


//    public Integer addProduct2( UserTable user, Integer productId, Integer amount){
//        ProductTable product = productRepository.findByProductId(productId).get();
//        CartItemTable cart = cartItemRepository.findByUserIdAndProductId(user, product);
//        try {
//            if (cart != null){
//                cart.setProductAmount(amount);
//            }else {
//                cart = new CartItemTable();
//                cart.setProductAmount(amount);
//                cart.setProductId(productId);
//            }
//        }catch (Exception e){
//            System.out.println("ERROR");
//        }
//        return cart.getCartId();
//    }


    public void addProduct(String userId, Integer product)throws BaseException {
        CartItemTable cartItem = new CartItemTable();
        cartItem.setUserId(userId);
        cartItem.setProductId(product);

        try {
        cartItemRepository.save(cartItem);

        }catch (Exception e){
            throw CartException.roleUserNotAllowed();
        }
    }

    public void updateCart(CartItemTable cart, Integer cartId, Integer product)throws BaseException {
        cart.setCartId(cartId);
        cart.setProductId(product);
        try {
            cartItemRepository.save(cart);
        }catch (Exception e){
            throw CartException.roleUserNotAllowed();
        }
    }




}
