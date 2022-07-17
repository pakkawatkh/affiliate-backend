package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.AffiliateException;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.repository.AffiliateRepository;
import api.affiliate.api.affiliate.repository.OrderRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final PasswordEncoder passwordEncoder;

    public OrderService(OrderRepository orderRepository, PasswordEncoder passwordEncoder) {
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public List<OrderListTable> findAllOrder() {
        List<OrderListTable> order = orderRepository.findAll();
        return order;
    }


    public List<OrderListTable> findMyOrder(String userId) {
        List<OrderListTable> order = orderRepository.findAllOrderByUserId(userId);
        return order;
    }





    public void createOrder(String user, String day, String time, String img) throws BaseException {
    OrderListTable order = new OrderListTable();
        order.setUserId(user);
        order.setImage(img);
        order.setDay(day);
        order.setTime(time);
        try {
            orderRepository.save(order);
        }catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }
    }




}
