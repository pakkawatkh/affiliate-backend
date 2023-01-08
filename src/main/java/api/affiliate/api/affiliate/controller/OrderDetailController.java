package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.OrderDetailBusiness;
import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.order.OrderRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/order-detail")
@Log4j2
public class OrderDetailController {


    public final OrderDetailBusiness orderDetailBusiness;

    public OrderDetailController(OrderDetailBusiness orderDetailBusiness) {
        this.orderDetailBusiness = orderDetailBusiness;
    }


    @GetMapping("/getAllOrder")
    public ResponseEntity<Object> getAllOrder(){
        List<OrderDetailTable> order = orderDetailBusiness.findAllOrderDetail();
        return ResponseEntity.ok(order);
    }



    @PostMapping("/addProducts")
    public ResponseEntity<Object> addProduct(@RequestBody OrderRequest order){
        log.info(order);
        Object product = orderDetailBusiness.addProduct(order);
        return ResponseEntity.ok(product);
    }




}
