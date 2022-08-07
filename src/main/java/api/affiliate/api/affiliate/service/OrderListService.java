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
            return orderRepository.save(order);

        }catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }

    }


    public void saveTotalPrice(OrderListTable order)throws BaseException{
        try {
            orderRepository.save(order);
        }catch (Exception e){
//            TODO: ERROR
        }
    }


    public void deleteOrderList(OrderListTable orderList)throws BaseException{
        try {
            orderRepository.delete(orderList);
        }catch (Exception e){
//            TODO: ERROR
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

    public OrderListTable getOrderListDetailByIdAndStore(Integer id,Integer storeId){
        return orderRepository.getOrderListDetailByIdAndStore(storeId, id);
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
