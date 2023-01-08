package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AffiliateBusiness;
import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.business.StoreBisiness;
import api.affiliate.api.affiliate.business.WithdrawBusiness;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.model.link.DetailLinkResponse;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.model.order.OrderTrackingRequest;
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
    private final WithdrawBusiness withdrawBusiness;

    private final AffiliateBusiness affiliateBusiness;


    //    GET
    @GetMapping("/getAll-store")
    public List<StoreTable> getAllStore() {
        List<StoreTable> store = storeBisiness.findAllStore();
        return store;
    }


    @GetMapping("/getMyProfile-store")
    public Object getMyProfileStore() {
        Object store = storeBisiness.getMyProfileStore();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-order-status-payment-by-store")
    public Object getOrderStatusIsPayment() {
        Object store = orderListBusiness.getOrderStatusPaymentByStore();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-order-status-success-by-store")
    public Object getOrderStatusIsSuccess() {
        Object store = orderListBusiness.getOrderStatusSuccessByStore();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-order-share-success-by-store")
    public Object getOrderStatusIsSuccessAndDlvIsTrue() {
        Object store = orderListBusiness.getOrderStatusSuccessByStore();
        return ResponseEntity.ok(store);
    }


    @GetMapping("/get-my-total-price")
    public ResponseEntity<Object> getMyTotalPriceByOrderStatusSuccess() {
        Object order = orderListBusiness.getTotalPriceByOrderStatusSuccess();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-all-order-status-withdraw-success-by-store")
    public ResponseEntity<Object> getAllOrderStatusWithDrawSuccess() {
        Object withdraw = orderListBusiness.getAllOrderStatusWithDrawSuccessByStore();
        return ResponseEntity.ok(withdraw);
    }


    @GetMapping("/get-all-order-status-wait-payment-by-store")
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        List<OrderResponse> order = orderListBusiness.getOrderStatusWaitPaymentByStore();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-my-order-deliver-is-true-by-store")
    public ResponseEntity<List<OrderResponse>> getOrderDeliverStatusIsTrueByStore() {
        List<OrderResponse> order = orderListBusiness.getOrderDeliverStatusIsTrueByStore();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-share-link-success-by-store/{id}")
    public ResponseEntity<DetailLinkResponse> getWithdrawById(@PathVariable Integer id) {
        DetailLinkResponse link = affiliateBusiness.getShareLinkSuccessByStore(id);
        return ResponseEntity.ok(link);
    }


    //    POST
    @PostMapping("/store-register")
    public ResponseEntity<Object> register(@RequestBody StoreRegisterRequest request) {
        Object register = storeBisiness.register(request);
        return ResponseEntity.ok(register);
    }


    //    PUT
    @PutMapping("/store-update")
    public ResponseEntity<Object> updateStore(@RequestBody StoreRegisterRequest request) {
        Object update = storeBisiness.updateStore(request);
        return ResponseEntity.ok(update);

    }

    @PutMapping("/delete-myStore")
    public Object updateStatusStore() {
        Object store = storeBisiness.updateStatusStore();
        return ResponseEntity.ok(store);
    }


    @PutMapping("/update-order-success/{id}")
    public ResponseEntity<Object> updateOrderStatusIsSuccess(@PathVariable Integer id,
                                                             @RequestBody OrderTrackingRequest request) {
        Object update = orderListBusiness.updateOrderStatusIsSuccess(id, request);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-order-withdraw-money")
    public ResponseEntity<Object> updateOrderStatusIsWithDrawMoney() {
        Object update = withdrawBusiness.createWithdrawByOrder();
        return ResponseEntity.ok(update);
    }


}
