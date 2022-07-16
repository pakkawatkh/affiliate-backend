package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AffiliateBusiness;
import api.affiliate.api.affiliate.business.OrderBusiness;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    public final OrderBusiness orderBusiness;

    public OrderController(OrderBusiness orderBusiness) {
        this.orderBusiness = orderBusiness;
    }



    @GetMapping("/getAllOrder")
    public ResponseEntity<Object> getAllOrder() throws BaseException {
        List<OrderListTable> order = orderBusiness.getAllOrder();
        return ResponseEntity.ok(order);
    }

    //    POST
    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@RequestParam(value = "file") MultipartFile file) throws BaseException {
        Object order = orderBusiness.createOrder(file);
        return ResponseEntity.ok(order);
    }






}
