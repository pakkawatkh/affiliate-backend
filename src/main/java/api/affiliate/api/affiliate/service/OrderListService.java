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

    public final OrderListRepository orderListRepository;
    public final PasswordEncoder passwordEncoder;


    public OrderListService(OrderListRepository orderRepository, PasswordEncoder passwordEncoder) {
        this.orderListRepository = orderRepository;
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




    public OrderListTable createOrder(String user, String fullName, String tel, String address, String sub,
                                      String district, String province, Integer postalCode, Integer storeId) throws BaseException {
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

        }catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }

    }


    public void saveTotalPrice(OrderListTable order)throws BaseException{
        try {
            orderListRepository.save(order);
        }catch (Exception e){
//            TODO: ERROR
        }
    }


    public void deleteOrderList(OrderListTable orderList)throws BaseException{
        try {
            orderListRepository.delete(orderList);
        }catch (Exception e){
//            TODO: ERROR
        }
    }


    public List<OrderListTable> getOrderByStoreId(Integer storeId){
        List<OrderListTable> order = orderListRepository.getOrderByStoreId(storeId);
        System.out.println(order.toString());
        return order;
    }

    public OrderListTable getOrderListDetailByIdAndStore(Integer id,Integer storeId)throws BaseException{
        OrderListTable orderList = orderListRepository.getOrderListDetailByIdAndStore(storeId, id);
        if (orderList == null){
            throw OrderException.orderNull();
        }

        return orderList;
    }

    public OrderListTable getOrderListDetailByIdAndUser(Integer id, String userId)throws BaseException{
        OrderListTable orderList = orderListRepository.getOrderListDetailByIdAndUser(userId, id);
        if (orderList == null){
            throw OrderException.orderNull();
        }

        return orderList;
    }





    public void addSlip(OrderListTable order, String img) throws BaseException{
        order.setImage(img);
        order.setDate(new Date());
        try {
            orderListRepository.save(order);
        }catch (Exception e){
            throw OrderException.orderNull();
        }
    }




}
