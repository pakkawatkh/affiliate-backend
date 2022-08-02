package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    public final OrderDetailRepository orderDetailRepository;


    public OrderDetailService(OrderDetailRepository orderDetailRepository) {

        this.orderDetailRepository = orderDetailRepository;
    }


    public List<OrderDetailTable> findAllOrder() {
        List<OrderDetailTable> order = orderDetailRepository.findAll();
        return order;
    }


    public List<OrderDetailTable> findAllByStoreId(Integer storeId) {
        List<OrderDetailTable> order = orderDetailRepository.findAllByStoreId(storeId);
        return order;
    }


    public void addProductIsOrder(Integer productId, Integer productPrice, Integer productAmount, Integer productTotal,
                                  Integer storeId, String userId, String fullName, Integer tel, String address, String sub,
                                  String district, String province, Integer postalCode) throws BaseException {
        OrderDetailTable order = new OrderDetailTable();
        order.setProductId(productId);
        order.setProductPrice(productPrice);
        order.setProductAmount(productAmount);
        order.setProductTotal(productTotal);
        order.setStoreId(storeId);
        order.setUserId(userId);
        order.setFullName(fullName);
        order.setTel(tel);
        order.setAddress(address);
        order.setSub(sub);
        order.setDistrict(district);
        order.setProvince(province);
        order.setPostalCode(postalCode);
        try {
            orderDetailRepository.save(order);
        }catch (Exception e) {
            throw OrderException.orderRequestInvalid();
        }
    }

}
