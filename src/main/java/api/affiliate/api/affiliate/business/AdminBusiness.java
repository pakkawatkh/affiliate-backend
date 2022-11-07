package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.mapper.OrderListMapper;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.service.AdminService;
import api.affiliate.api.affiliate.service.OrderDetailService;
import api.affiliate.api.affiliate.service.OrderListService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminBusiness {


    private final TokenService tokenService;
    private final AdminService adminService;
    private final OrderListService orderListService;
    private final OrderListMapper orderListMapper;
    private final StoreService storeService;
    private final OrderDetailService orderDetailService;


//    public List<UserTable> findAllUser(){
//        UserTable user = tokenService.getUserByToken();
//        System.out.println("USER"+user);
//        checkRoleIsAdmin(user);
//        List<UserTable> user1 = adminService.getAllUser();
//        return user1;
//    }


    public List<UserTable> findAllUser() {
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER" + user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.USER);
        return user1;
    }


    public List<UserTable> findAllStore() {
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER " + user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.ST_AF, UserTable.Role.STORE);
        System.out.println(user1);
        return user1;
    }


    public List<UserTable> findAllAffiliate() {
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER " + user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.AFFILIATE, UserTable.Role.ST_AF);
        System.out.println(user1);
        return user1;
    }


    public List<OrderResponse> getOrderUserById(String id) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        List<OrderListTable> orderList = adminService.findOrderByUserId(id);
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }


    public List<OrderResponse> getOrderByStoreId(Integer id) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        StoreTable store = storeService.findByStoreId(id);
        List<OrderListTable> orderList = orderListService.getOrderByStoreId(store.getStoreId());
        List<OrderResponse> orderResponses = orderListMapper.toOrderResponse(orderList);
        for (OrderResponse order : orderResponses) {
            List<OrderDetailTable> details = orderDetailService.findAllByOrderListId(order.getOrderListId());
            order.setDetail(details);
        }
        return orderResponses;
    }


    public Object getTotalPriceOrderByStore(Integer id) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        StoreTable store = storeService.findByStoreId(id);
        List<OrderListTable> orderList = orderListService.getOrderByStoreId(store.getStoreId());
        int total = 0;
        for (OrderListTable order : orderList) {
            total = total + order.getTotalPrice();
        }
        System.out.println(total);
        store.setTotalPrice(total);
        adminService.saveTotalPrice(store);
        return store;
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