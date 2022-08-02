package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
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



    //    POST
    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@RequestParam(value = "file") MultipartFile file,
                                              @RequestParam(value = "order")Object order) throws BaseException {
        Object create = orderBusiness.createOrder(file, order);
        return ResponseEntity.ok(create);
    }


    @PutMapping("/update-order-payment/{id}")
    public ResponseEntity<Object> updateOrderStatusIsPayment(@PathVariable Integer id) throws BaseException {
        Object update = orderBusiness.updateOrderStatusIsPayment(id);
        return ResponseEntity.ok(update);
    }











}
