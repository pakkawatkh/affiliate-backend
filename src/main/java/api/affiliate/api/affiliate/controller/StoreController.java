package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.business.StoreBisiness;
import api.affiliate.api.affiliate.business.WithdrawBusiness;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreBisiness storeBisiness;
    private final OrderListBusiness orderListBusiness;

    private  final WithdrawBusiness withdrawBusiness;


    //    GET
    @GetMapping("/getAll-store")
    public List<StoreTable> getAllStore() {
        List<StoreTable> store = storeBisiness.findAllStore();
        return store;
    }


    @GetMapping("/getMyProfile-store")
    public Object getMyProfileStore(){
        Object store = storeBisiness.getMyProfileStore();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-order-status-payment")
    public Object getOrderStatusIsPayment(){
        Object store = orderListBusiness.getOrderStatusPayment();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-my-total-price")
    public ResponseEntity<Object> getMyTotalPriceByOrderStatusSuccess(){
        Object order = orderListBusiness.getTotalPriceByOrderStatusSuccess();
        return ResponseEntity.ok(order);
    }


    //    POST
    @PostMapping("/store-register")
    public ResponseEntity<Object> register(@RequestBody StoreRegisterRequest request) throws BaseException {
        System.out.println(request);
        Object register = storeBisiness.register(request);
        return ResponseEntity.ok(register);
    }


    //    PUT
    @PutMapping("/store-update")
    public ResponseEntity<Object> updateStore(@RequestBody StoreRegisterRequest request) throws BaseException {
        Object update = storeBisiness.updateStore(request);
        return ResponseEntity.ok(update);

    }

    @PutMapping("/delete-myStore")
    public Object updateStatusStore() throws BaseException {
        Object store = storeBisiness.updateStatusStore();
        return ResponseEntity.ok(store);
    }


    @PutMapping("/update-order-success/{id}")
    public ResponseEntity<Object> updateOrderStatusIsSuccess(@PathVariable Integer id) throws BaseException {
        Object update = orderListBusiness.updateOrderStatusIsSuccess(id);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-order-withdraw-money")
    public ResponseEntity<Object> updateOrderStatusIsWithDrawMoney(){
        Object update = withdrawBusiness.createWithdrawByOrder();
        return ResponseEntity.ok(update);
    }


}
