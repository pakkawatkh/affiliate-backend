package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminBusiness adminBusiness;
    private final OrderListBusiness orderBusiness;




    //   GET
    @GetMapping("/get-all-user")
    public List<UserTable> getAllUser() {
        List<UserTable> user = adminBusiness.findAllUser();
        return user;
    }

    @GetMapping("/get-all-store")
    public List<UserProfileResponse> getAllStore() {
        List<UserProfileResponse> user = adminBusiness.findAllStore();
        return user;
    }

    @GetMapping("/get-all-affiliate")
    public List<UserProfileResponse> getAllAffiliate() {
        List<UserProfileResponse> user = adminBusiness.findAllAffiliate();
        return user;
    }

    @GetMapping("/get-order-by-user-id/{id}")
    public List<OrderResponse> getOrderUserById(@PathVariable String id) {
        List<OrderResponse> user = adminBusiness.getOrderUserById(id);
        return user;
    }

    @GetMapping("/get-order-by-store-id/{id}")
    public List<OrderResponse> getOrderStoreById(@PathVariable Integer id){
        List<OrderResponse> user = adminBusiness.getOrderByStoreId(id);
        return user;
    }

    @GetMapping("/get-all-order")
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        List<OrderResponse> order = orderBusiness.getOrderStatusWaitPayment();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-total-order-by-store/{id}")
    public ResponseEntity<Object> getTotalPriceOrderByStore(@PathVariable Integer id){
        Object order = adminBusiness.getTotalPriceOrderByStore(id);
        return ResponseEntity.ok(order);
    }


//    PUT
    @PutMapping("/update-order-payment/{id}")
    public ResponseEntity<Object> updateOrderStatusIsPayment(@PathVariable Integer id){
        Object update = orderBusiness.updateOrderStatusIsPayment(id);
        return ResponseEntity.ok(update);
    }


    @PostMapping("/update-order-withdraw-success-and-attach-slip/{id}")
    public ResponseEntity<Object> updateOrderStatusIsWithDrawSuccessAndAttachSlip(@PathVariable Integer id){
        Object update = orderBusiness.updateOrderStatusIsWithDrawSuccessAndAttachSlip(id);
        return ResponseEntity.ok(update);
    }







}
