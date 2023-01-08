package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.business.WithdrawBusiness;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    public final UserBusiness userBusiness;
    private final AdminBusiness adminBusiness;
    private final OrderListBusiness orderBusiness;
    private final WithdrawBusiness withdrawBusiness;

    //   GET
    @GetMapping("/get-my-profile-by-admin")
    public Object getMyProfileByAdmin() {
        Object user = adminBusiness.findMyProfileByAdmin();
        return user;
    }


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
    public List<OrderResponse> getOrderStoreById(@PathVariable Integer id) {
        List<OrderResponse> user = adminBusiness.getOrderByStoreId(id);
        return user;
    }

    @GetMapping("/get-all-order-by-admin")
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        List<OrderResponse> order = orderBusiness.getOrderStatusWaitPaymentByAdmin();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-total-order-by-store/{id}")
    public ResponseEntity<Object> getTotalPriceOrderByStore(@PathVariable Integer id) {
        Object order = adminBusiness.getTotalPriceOrderByStore(id);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-all-order-status-withdraw-money")
    public ResponseEntity<Object> getAllOrderStatusWithDrawMoney() {
        Object order = adminBusiness.getAllOrderStatusWithDrawMoney();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-all-order-status-withdraw-success")
    public ResponseEntity<Object> getAllOrderStatusWithDrawSuccess() {
        Object order = adminBusiness.getAllOrderStatusWithDrawSuccess();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/get-withdraw-by-id/{id}")
    public ResponseEntity<Object> getWithdrawById(@PathVariable Integer id) {
        Object withdraw = withdrawBusiness.findByWithdrawById(id);
        return ResponseEntity.ok(withdraw);
    }


    //    PUT
    @PutMapping("/update-order-payment/{id}")
    public ResponseEntity<Object> updateOrderStatusIsPayment(@PathVariable Integer id) {
        Object update = orderBusiness.updateOrderStatusIsPayment(id);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-order-withdraw-success-and-add-slip/{id}")
    public ResponseEntity<Object> updateOrderStatusIsWithDrawSuccessAndAddSlip(@PathVariable Integer id,
                                                                               @RequestParam(value = "file", required = false) MultipartFile file) {
        Object update = orderBusiness.updateOrderStatusIsWithDrawSuccessAndAddSlip(file, id);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-profile-by-admin")
    public ResponseEntity<Object> updateProfile(@RequestParam(value = "file", required = false) MultipartFile file,
                                                @RequestParam(value = "profile") Object profile) {
        Object update = userBusiness.updateProfile(file, profile);
        return ResponseEntity.ok(update);
    }


    @PostMapping("/create-percent")
    public ResponseEntity<Object> createPercent(@RequestParam(value = "percent") Float percent) {
        Object update = adminBusiness.createPercent(percent);
        return ResponseEntity.ok(update);
    }


}
