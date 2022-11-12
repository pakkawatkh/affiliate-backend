package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.mapper.OrderListMapper;
import api.affiliate.api.affiliate.mapper.UserMapper;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import api.affiliate.api.affiliate.service.*;
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
    private final UserMapper userMapper;
    private final AffiliateService affiliateService;


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


    public List<UserProfileResponse> findAllStore() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.ST_AF, UserTable.Role.STORE);
        List<UserProfileResponse> response = userMapper.toUserProfileResponse(user1);
        for (UserProfileResponse r : response) {
            StoreTable store = storeService.findByUserId(r.getUserId());
            r.setStore(store);
        }
        return response;
    }


    public List<UserProfileResponse> findAllAffiliate() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.AFFILIATE, UserTable.Role.ST_AF);
        List<UserProfileResponse> response = userMapper.toUserProfileResponse(user1);
        for (UserProfileResponse r : response) {
            AffiliateTable affiliate = affiliateService.findByUserId(r.getUserId());
            r.setAffiliate(affiliate);
        }
        return response;
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