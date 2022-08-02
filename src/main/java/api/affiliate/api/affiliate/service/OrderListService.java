package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.repository.OrderListRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderListService {

    public final OrderListRepository orderRepository;
    public final PasswordEncoder passwordEncoder;


    public OrderListService(OrderListRepository orderRepository, PasswordEncoder passwordEncoder) {
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
//        getOrderByStoreId();
    }



    public List<OrderListTable> findAllOrder() {
        List<OrderListTable> order = orderRepository.findAll();
        return order;
    }


    public List<OrderListTable> findMyOrder(String userId) {
        List<OrderListTable> order = orderRepository.findAllOrderByUserId(userId);
        return order;
    }

    public OrderListTable findByOrderId(Integer orderId) {
        Optional<OrderListTable> order = orderRepository.findByOrderListId(orderId);
        return order.get();
    }






    public void createOrder(String user,String price, String day, String time, String img) throws BaseException {
        OrderListTable order = new OrderListTable();
        order.setUserId(user);
        order.setPrice(price);
        order.setDay(day);
        order.setTime(time);
        order.setImage(img);
        try {
            orderRepository.save(order);
        }catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }
    }



//    public void createOrder(Integer cart,String price, String day, String time, String img) throws BaseException {
//        OrderListTable order = new OrderListTable();
//        order.setCartId(cart);
//        order.setPrice(price);
//        order.setDay(day);
//        order.setTime(time);
//        order.setImage(img);
//        try {
//            orderRepository.save(order);
//            System.out.println("try");
//        }catch (Exception e) {
//            System.out.println("catch");
//            throw OrderException.orderRequestInvalid();
//        }
//    }

    public List<OrderListTable> getOrderByStoreId(Integer storeId){
        List<OrderListTable> order = orderRepository.getOrderByStoreId(storeId);
        System.out.println(order.toString());
        return order;
    }


    public void updateOrderStatusIsPayment(OrderListTable order) throws BaseException{
        order.setStatus("payment");
        order.setDate(new Date());
        try {
            orderRepository.save(order);
        }catch (Exception e){
            throw OrderException.orderNull();
        }
    }




}
