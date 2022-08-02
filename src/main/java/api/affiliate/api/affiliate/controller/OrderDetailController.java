package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.OrderDetailBusiness;
import api.affiliate.api.affiliate.business.OrderListBusiness;
import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.order.OrderRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order-detail")
@Log4j2
public class OrderDetailController {


    public final OrderDetailBusiness orderDetailBusiness;

    public OrderDetailController(OrderDetailBusiness orderDetailBusiness) {
        this.orderDetailBusiness = orderDetailBusiness;
    }


    @GetMapping("/getAllOrder")
    public ResponseEntity<Object> getAllOrder() throws BaseException {
        List<OrderDetailTable> order = orderDetailBusiness.getAllOrder();
        return ResponseEntity.ok(order);
    }


    @GetMapping("/getAllByStoreId")
    public ResponseEntity<Object> findAllByStoreId() throws BaseException {
        List<OrderDetailTable> order = orderDetailBusiness.findAllByStoreId();
        return ResponseEntity.ok(order);
    }


    @PostMapping("/addProducts")
    public ResponseEntity<Object> addProduct(@RequestBody OrderRequest order) throws BaseException {

//        System.out.println(order.getTel());
//        System.out.println(order.getAddress());
//        System.out.println(order.getSub());
//        System.out.println(order.getDistrict());
//        System.out.println(order.getProvince());
//        System.out.println(order.getPostalCode());

//        List<ProductRequest> products = order.getProducts();
//        for (ProductRequest product:products){
//            System.out.println("product id :" + product.getProductId());
//            System.out.println("product amount : " + product.getProductAmount());
//        }
        log.info(order);
        Object product = orderDetailBusiness.addProduct(order);
        return ResponseEntity.ok(product);
    }

}
