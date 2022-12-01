package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.repository.OrderListRepository;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderListService {

    public final OrderListRepository orderListRepository;

    public final PasswordEncoder passwordEncoder;

    public OrderListService(OrderListRepository orderListRepository, PasswordEncoder passwordEncoder) {
        this.orderListRepository = orderListRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<OrderListTable> findAllOrder() {
        List<OrderListTable> order = orderListRepository.findAll();
        return order;
    }


    public List<OrderListTable> findMyOrder(String userId) {
        List<OrderListTable> order = orderListRepository.findAllOrderByUserId(userId);
        return order;
    }

    public OrderListTable findByOrderId(Integer orderId) {
        Optional<OrderListTable> order = orderListRepository.findByOrderListId(orderId);
        return order.get();
    }


    @SneakyThrows
    public OrderListTable createOrder(String user, String fullName, String tel, String address, String sub,
                                      String district, String province, Integer postalCode, Integer storeId) {
        OrderListTable order = new OrderListTable();
        order.setUserId(user);
        order.setFullName(fullName);
        order.setTel(tel);
        order.setAddress(address);
        order.setSub(sub);
        order.setDistrict(district);
        order.setProvince(province);
        order.setPostalCode(postalCode);
        order.setStoreId(storeId);
        try {
            return orderListRepository.save(order);

        } catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }

    }


    public void saveTotalPrice(OrderListTable order) {
        try {
            orderListRepository.save(order);
        } catch (Exception e) {
//            TODO: ERROR
        }
    }


    public void deleteOrderList(OrderListTable orderList) {
        try {
            orderListRepository.delete(orderList);
        } catch (Exception e) {
//            TODO: ERROR
        }
    }


    public List<OrderListTable> getOrderByStoreId(Integer storeId) {
        List<OrderListTable> order = orderListRepository.getOrderByStoreId(storeId);
        return order;
    }


    public List<OrderListTable> getOrderStatus(Integer storeId, String status) {
        List<OrderListTable> order = orderListRepository.getOrderStatus(storeId, status);
        return order;
    }

    public List<OrderListTable> getOrderStatus(String status) {
        List<OrderListTable> order = orderListRepository.getOrderStatus(status);
        return order;
    }

    @SneakyThrows
    public OrderListTable getOrderListDetailByIdAndStore(Integer id, Integer storeId) {
        OrderListTable orderList = orderListRepository.getOrderListDetailByIdAndStore(storeId, id);
        if (orderList == null) {
            throw OrderException.orderNull();
        }

        return orderList;
    }

    @SneakyThrows
    public OrderListTable getOrderListDetailByIdAndUser(Integer id, String userId) {
        OrderListTable orderList = orderListRepository.getOrderListDetailByIdAndUser(userId, id);
        if (orderList == null) {
            throw OrderException.orderNull();
        }

        return orderList;
    }


    public Integer getTotalPriceByOrderStatusSuccess(Integer storeId) {
        boolean exists = orderListRepository.existsByStatusAndStoreId("success", storeId);
        if (exists) {
            int count = orderListRepository.getTotalPriceByOrderStatusSuccess(storeId);
            return count;
        } else {
            return 0;
        }
    }


    @SneakyThrows
    public void addSlip(OrderListTable order, String img) {
        order.setImage(img);
        order.setDate(new Date());
        order.setStatus("wait payment");
        try {
            orderListRepository.save(order);
        } catch (Exception e) {
            throw OrderException.orderNull();
        }
    }


    @SneakyThrows
    public void updateOrderStatusIsPayment(OrderListTable order) {
        order.setStatus("payment");
        order.setDate(new Date());
        try {
            orderListRepository.save(order);
        } catch (Exception e) {
            throw OrderException.orderNull();
        }
    }


    @SneakyThrows
    public void updateOrderStatusIsSuccess(OrderListTable order) {
        order.setStatus("success");
        order.setDate(new Date());
        try {
            orderListRepository.save(order);
        } catch (Exception e) {
            throw OrderException.orderNull();
        }
    }


    @SneakyThrows
    public void updateOrderStatusIsWithDrawMoney(Integer withdrawId, Integer storeId) {
        try {
            orderListRepository.updateOrderStatusIsWithDrawMoney(withdrawId, storeId);
        } catch (Exception e) {
            throw OrderException.orderNull();
        }
    }

}
