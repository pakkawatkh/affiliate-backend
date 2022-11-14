package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.mapper.OrderListMapper;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.service.*;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListBusiness {


    public final OrderListService orderService;
    public final UserService userService;
    private final StoreService storeService;
    private final TokenService tokenService;
    private final FileService fileService;
    private final OrderDetailService orderDetailService;
    private final OrderListMapper orderListMapper;



    public List<OrderResponse> getOrderByStoreId() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        List<OrderListTable> orderList = orderService.getOrderByStoreId(store.getStoreId());
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }


    public List<OrderResponse> getOrderStatusPayment() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        List<OrderListTable> orderList = orderService.getOrderStatus(store.getStoreId(), "payment");
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }

    public List<OrderResponse> getOrderStatusWaitPayment() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        List<OrderListTable> orderList = orderService.getOrderStatus("wait payment");
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }


    public OrderResponse getDetailByIdAndStore(Integer id) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        OrderListTable order = orderService.getOrderListDetailByIdAndStore(id, store.getStoreId());
        OrderResponse response = orderListMapper.toOrderResponse(order);
        List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(response.getOrderListId());
        response.setDetail(details);

        return response;
    }


    public OrderResponse getDetailById(Integer id) {
        UserTable user = tokenService.getUserByToken();
        OrderListTable order = orderService.getOrderListDetailByIdAndUser(id, user.getUserId());
        OrderResponse response = orderListMapper.toOrderResponse(order);
        List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(response.getOrderListId());
        response.setDetail(details);

        return response;
    }


    public List<OrderResponse> getMyOrderList() {
        UserTable user = tokenService.getUserByToken();
        List<OrderListTable> orderList = orderService.findMyOrder(user.getUserId());
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }


    public Object getTotalPriceByOrderStatusSuccess() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        Object orderList = orderService.getTotalPriceByOrderStatusSuccess(store.getStoreId());
        return new Response().ok("", "ยอดเงินที่สามารถถอนได้", orderList);
    }


    @SneakyThrows
    public Object addSlip(MultipartFile file, Integer orderId) {
        UserTable user = tokenService.getUserByToken();
        OrderListTable order = orderService.findByOrderId(orderId);
        UserTable user1 = userService.findById(order.getUserId());
        if (user != user1){
            throw UserException.roleUserNotAllowed();
        }
        String img;
        img = file != null ? fileService.saveImg(file, "/uploads/orders") : order.getImage();
        orderService.addSlip(order, img);
        return new Response().success("add slip success");
    }


    public Object updateOrderStatusIsPayment(Integer orderId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        OrderListTable order = orderService.findByOrderId(orderId);
        System.out.println("ORDER " + order);
        orderService.updateOrderStatusIsPayment(order);
        return new Response().success("update order status payment");
    }


    public Object updateOrderStatusIsSuccess(Integer orderId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        OrderListTable order = orderService.findByOrderId(orderId);
        System.out.println("ORDER " + order);
        orderService.updateOrderStatusIsSuccess(order);
        return new Response().success("update order status success");
    }


    public Object updateOrderStatusIsWithDrawMoney() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        System.out.println(store);
        orderService.updateOrderStatusIsWithDrawMoney(store.getStoreId());
        return new Response().success("update order status withdraw money");
    }


    public Object updateOrderStatusIsWithDrawSuccess(Integer orderId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        OrderListTable order = orderService.findByOrderId(orderId);
        System.out.println("ORDER " + order);
        orderService.updateOrderStatusIsWithDrawSuccess(order);
        return new Response().success("update order status withdraw success");
    }




    @SneakyThrows
    public void checkRoleIsStore(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE) || role.equals(UserTable.Role.ADMIN);
        if (checkRole) {
            throw StoreException.roleUserNotAllowed();
        }
    }

    @SneakyThrows
    public void checkRoleIsAdmin(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.STORE)
                || role.equals(UserTable.Role.AFFILIATE)
                || role.equals(UserTable.Role.USER)
                || role.equals(UserTable.Role.ST_AF);
        if (checkRole) {
            throw UserException.roleUserNotAllowed();
        }
    }
}