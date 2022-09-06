package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/order-list")
public class OrderListController {

    public final OrderListBusiness orderBusiness;

    public OrderListController(OrderListBusiness orderBusiness) {
        this.orderBusiness = orderBusiness;
    }


    @GetMapping("/getAllOrder")
    public ResponseEntity<Object> getAllOrder() throws BaseException {
        List<OrderListTable> order = orderBusiness.getAllOrder();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/getMyOrder")
    public ResponseEntity<Object> getMyOrder() throws BaseException {
        List<OrderListTable> order = orderBusiness.getOrderByStoreId();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getOrderDetail")
    public ResponseEntity<OrderResponse> getOrderDetail(@RequestParam(name = "id") Integer orderId) throws BaseException {
        OrderResponse order = orderBusiness.getDetailById(orderId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/getMyOrderList")
    public ResponseEntity<List<OrderResponse>> getMyOrderList() throws BaseException {
        List<OrderResponse> order = orderBusiness.getMyOrderList();
        return ResponseEntity.ok(order);
    }




    @PutMapping("/add-slip-by-order/{id}")
    public ResponseEntity<Object> addSlip(@PathVariable Integer id,
                                          @RequestParam(value = "file", required = false) MultipartFile file) throws BaseException {
        Object update = orderBusiness.addslip(file, id);
        return ResponseEntity.ok(update);
    }


}
