package api.affiliate.api.affiliate.model;

import api.affiliate.api.affiliate.entity.CartItemTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.*;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import api.affiliate.api.affiliate.model.product.ProductCreateRequest;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapObject {
    public UserRegisterRequest toRegister(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        UserRegisterRequest map;
        try {
            map = mapper.readValue((String) result, UserRegisterRequest.class);
        } catch (JsonProcessingException e) {
            throw UserException.userRequestInvalid();
        }
        return map;
    }


    public ProductCreateRequest toCreateProduct(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        ProductCreateRequest map;
        try {
            map = mapper.readValue((String) result, ProductCreateRequest.class);
        } catch (JsonProcessingException e) {
            throw ProductException.productNull();
        }
        return map;
    }

    public CartItemTable toCreateCart(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        CartItemTable map;
        try {
            map = mapper.readValue((String) result, CartItemTable.class);
        } catch (JsonProcessingException e) {
            throw CartException.cartRequestInvalid();
        }
        return map;
    }



    public OrderListTable toCreateOrder(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        OrderListTable map;
        try {
            map = mapper.readValue((String) result, OrderListTable.class);
        } catch (JsonProcessingException e) {
            throw UserException.userRequestInvalid();
        }
        return map;
    }


    public AffiliateRegisterRequest toRegisterAffiliate(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        AffiliateRegisterRequest map;
        try {
            map = mapper.readValue((String) result, AffiliateRegisterRequest.class);
        } catch (JsonProcessingException e) {
            throw AffiliateException.affiliateRequestInvalid();
        }
        return map;
    }


    public StoreRegisterRequest toRegisterStore(Object result) throws BaseException {
        ObjectMapper mapper = new ObjectMapper();
        StoreRegisterRequest map;
        try {
            map = mapper.readValue((String) result, StoreRegisterRequest.class);
        } catch (JsonProcessingException e) {
            throw StoreException.storeRequestInvalid();
        }
        return map;
    }
}
