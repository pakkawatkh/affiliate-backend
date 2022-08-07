package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.mapper.OrderListMapper;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.service.*;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListBusiness {


    public final OrderListService orderService;
    public final UserService userService;

    public final CartService cartService;

    private final StoreService storeService;
    private final TokenService tokenService;
    private final FileService fileService;

    private final OrderDetailService orderDetailService;

    private final OrderListMapper orderListMapper;

    public OrderListBusiness(TokenService tokenService, OrderListService orderService, UserService userService, CartService cartService, StoreService storeService, FileService fileService, OrderDetailService orderDetailService, OrderListMapper orderListMapper) {
        this.tokenService = tokenService;
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.storeService = storeService;
        this.fileService = fileService;
        this.orderDetailService = orderDetailService;
        this.orderListMapper = orderListMapper;
    }



    public List<OrderListTable> getAllOrder() throws BaseException {
        List<OrderListTable> order = orderService.findAllOrder();
        if (order.isEmpty()){
            throw OrderException.orderNull();
        }
        return order;
    }


//    public List<OrderListTable> getMyOrder() throws BaseException {
//        UserTable user = tokenService.getUserByToken();
//        checkRoleIsStore(user);
//        StoreTable store = storeService.findByUserId2(user);
//        List<OrderListTable> order = orderService.findMyOrder(store.getUserId());
//        return order;
//    }


    public List<OrderListTable> getOrderByStoreId() throws BaseException{
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        List<OrderListTable> order = orderService.getOrderByStoreId(store.getStoreId());
        return order;
    }

    public OrderResponse getDetailById(Integer id) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        OrderListTable order = orderService.getOrderListDetailByIdAndStore(id, store.getStoreId());
        OrderResponse response = orderListMapper.toOrderResponse(order);
        List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(response.getOrderListId());
        response.setDetail(details);

        return response;
    }


//    public Object createOrder(MultipartFile file) throws BaseException {
//        UserTable user = tokenService.getUserByToken();
//        UserTable.Role role = user.getRole();
//        if  (role.equals(UserTable.Role.ADMIN)){
//            throw OrderException.roleUserNotAllowed();
//        }
//        String img = fileService.saveImg(file, "/uploads/orders");
//        orderService.createOrder(user.getUserId(), img);
//        return new Response().success("create order success");
//    }

    public void checkRoleIsStore(UserTable user) throws BaseException {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER)
                || role.equals(UserTable.Role.AFFILIATE)
                || role.equals(UserTable.Role.ADMIN);
        if (checkRole){
            throw ProductException.roleUserNotAllowed();
        }
    }

    public Object updateOrderStatusIsPayment(Integer orderId) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        OrderListTable order = orderService.findByOrderId(orderId);
        System.out.println("ORDER " + order);
        orderService.updateOrderStatusIsPayment(order);
        return new Response().success("update order status payment");
    }


}