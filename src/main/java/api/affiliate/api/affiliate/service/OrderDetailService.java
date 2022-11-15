package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.repository.OrderDetailRepository;
import api.affiliate.api.affiliate.repository.OrderListRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    public final OrderDetailRepository orderDetailRepository;

    public final OrderListRepository orderListRepository;


    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderListRepository orderListRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderListRepository = orderListRepository;
    }


    public List<OrderDetailTable> findAllOrderDetail() {
        List<OrderDetailTable> order = orderDetailRepository.findAll();
        return order;
    }


//    public List<OrderDetailTable> findAllByStoreId(Integer storeId) {
//        List<OrderDetailTable> order = orderDetailRepository.findAllByStoreId(storeId);
//        return order;
//    }


//    public Optional<OrderDetailTable> getOrderIdMax() {
//        Optional<OrderDetailTable> order = orderDetailRepository.getOrderIdMax();
//        return order;
//    }


    public OrderDetailTable addProductIsOrder(Integer productId, String productName, Integer productPrice,
                                              Integer productAmount, Integer productTotal,Integer orderListId) {
        OrderDetailTable order = new OrderDetailTable();
        order.setProductId(productId);
        order.setProductName(productName);
        order.setProductPrice(productPrice);
        order.setAmount(productAmount);
        order.setProductTotal(productTotal);
        order.setOrderListId(orderListId);
//        order.setStoreId(storeId);
//
//        try {
//            orderDetailRepository.save(order);
//        }catch (Exception e) {
//            throw OrderException.orderRequestInvalid();
//        }
        return order;
    }


    @SneakyThrows
    public List<OrderDetailTable> saveAll(OrderListTable orderList, List<OrderDetailTable> detail) {
        try {
        return orderDetailRepository.saveAll(detail);
        }catch (Exception e){
            orderDetailRepository.deleteByOrderListId(orderList.getOrderListId());
            orderListRepository.delete(orderList);
            throw  OrderException.orderRequestInvalid();
        }
    }

    @SneakyThrows
    public List<OrderDetailTable> findAllByOrderListId(Integer orderId){
        try {
            return orderDetailRepository.findAllByOrderListId(orderId);
        }catch (Exception e){
            throw OrderException.orderNull();
        }
    }


}
