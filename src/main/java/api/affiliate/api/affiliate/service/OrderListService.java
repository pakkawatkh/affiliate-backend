package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.repository.OrderRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListService {

    public final OrderRepository orderRepository;
    public final PasswordEncoder passwordEncoder;


    public OrderListService(OrderRepository orderRepository, PasswordEncoder passwordEncoder) {
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


    public List<OrderListTable> getOrderByStoreId(Integer storeId){
        List<OrderListTable> order = orderRepository.getOrderByStoreId(storeId);
        System.out.println(order.toString());
        return order;
    }




}
