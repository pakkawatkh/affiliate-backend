package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/order-list")
public class OrderListController {

    private final OrderListBusiness orderBusiness;
    private final AdminBusiness adminBusiness;


    @GetMapping("/get-my-order-status-payment")
    public ResponseEntity<List<OrderResponse>> getMyOrder(){
        List<OrderResponse> order = orderBusiness.getOrderStatusPayment();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-my-order-status-success")
    public ResponseEntity<List<OrderResponse>> getMyOrderStatusIsSuccess(){
        List<OrderResponse> order = orderBusiness.getOrderStatusSuccess();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/get-my-order-status-is-true")
    public ResponseEntity<List<OrderResponse>> getMyOrderStatusIsTrue(){
        List<OrderResponse> order = orderBusiness.getOrderStatusIsTrue();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/get-my-order-deliver-is-true")
    public ResponseEntity<List<OrderResponse>> getMyOrderDeliverIsTrue(){
        List<OrderResponse> order = orderBusiness.getOrderDeliverStatusIsTrue();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getOrderDetail")
    public ResponseEntity<OrderResponse> getOrderDetail(@RequestParam(name = "id") Integer orderId){
        OrderResponse order = orderBusiness.getDetailById(orderId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/get-my-order-detail")
    public ResponseEntity<OrderResponse> getOrderDetailByStore(@RequestParam(name = "id") Integer orderId){
        OrderResponse order = orderBusiness.getDetailByIdAndStore(orderId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/getMyOrderList")
    public ResponseEntity<List<OrderResponse>> getMyOrderList(){
        List<OrderResponse> order = orderBusiness.getMyOrderList();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/get-detail-admin")
    public Object getDetailAdmin(){
        Object user = adminBusiness.getDetailAdmin();
        return user;
    }




    @PutMapping("/add-slip-by-order/{id}")
    public ResponseEntity<Object> addSlip(@PathVariable Integer id,
                                          @RequestParam(value = "file", required = false) MultipartFile file){
        Object update = orderBusiness.addSlip(file, id);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-order-false/{id}")
    public ResponseEntity<Object> updateOrderStatusIsFalse(@PathVariable Integer id) {
        Object update = orderBusiness.updateOrderStatusIsFalse(id);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/update-order-deliver-status-is-true/{id}")
    public ResponseEntity<Object> updateOrderDeliverStatus(@PathVariable Integer id) {
        Object update = orderBusiness.updateOrderDeliverStatus(id);
        return ResponseEntity.ok(update);
    }

}
