package api.affiliate.api.affiliate.controller;

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

    public final OrderListBusiness orderBusiness;


//    @GetMapping("/getMyOrder")
//    public ResponseEntity<List<OrderResponse>> getMyOrder(){
//        List<OrderResponse> order = orderBusiness.getOrderByStoreId();
//        return ResponseEntity.ok(order);
//    }

    @GetMapping("/getMyOrder")
    public ResponseEntity<List<OrderResponse>> getMyOrder(){
        List<OrderResponse> order = orderBusiness.getOrderStatusPayment();
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







    @PutMapping("/add-slip-by-order/{id}")
    public ResponseEntity<Object> addSlip(@PathVariable Integer id,
                                          @RequestParam(value = "file", required = false) MultipartFile file){
        Object update = orderBusiness.addSlip(file, id);
        return ResponseEntity.ok(update);
    }



//    @PutMapping("/update-order-payment/{id}")
//    public ResponseEntity<Object> updateOrderStatusIsPayment(@PathVariable Integer id) throws BaseException {
//        Object update = orderBusiness.updateOrderStatusIsPayment(id);
//        return ResponseEntity.ok(update);
//    }


}
