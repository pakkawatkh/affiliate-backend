package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminBusiness adminBusiness;




    //   GET
    @GetMapping("/get-all-user")
    public List<UserTable> getAllUser() {
        List<UserTable> user = adminBusiness.findAllUser();
        return user;
    }

    @GetMapping("/get-all-store")
    public List<UserTable> getAllStore() {
        List<UserTable> user = adminBusiness.findAllStore();
        return user;
    }

    @GetMapping("/get-all-affiliate")
    public List<UserTable> getAllAffiliate() {
        List<UserTable> user = adminBusiness.findAllAffiliate();
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


    @GetMapping("/get-total-order-by-store/{id}")
    public ResponseEntity<Object> getTotalPriceOrderByStore(@PathVariable Integer id){
        Object order = adminBusiness.getTotalPriceOrderByStore(id);
        return ResponseEntity.ok(order);
    }

}
