package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.exception.WithdrawException;
import api.affiliate.api.affiliate.model.product.ProductRequest;
import api.affiliate.api.affiliate.service.OrderListService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.WithdrawService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.persister.walking.spi.WalkingException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WithdrawBusiness {

    public final OrderListService orderService;
    private final WithdrawService withdrawService;
    private final TokenService tokenService;
    private final StoreService storeService;


//    @SneakyThrows
//    public Object createWithdrawByOrder(OrderListTable order) {
//        UserTable user = tokenService.getUserByToken();
//        checkRoleIsStore(user);
//        StoreTable store = storeService.findByStoreId(order.getStoreId());
//        OrderListTable orderList = orderService.findByOrderId(order.getOrderListId());
//        OrderListTable order1 = (OrderListTable) orderService.getTotalPriceByOrderStatusSuccess(store.getStoreId());
//        orderService.findAllOrder(orderList.getOrderListId());
//        try {
//            for (orderList.getOrderListId() : order1){
//
//            }
//        }catch (Exception e){
//            throw WithdrawException.createFail();
//        }
//        WithdrawTable withdraw = withdrawService.createWithdrawByOrder(order1.getTotalPrice());
//        orderList.setStatus("withdraw money");
//        return
//    }


    @SneakyThrows
    public void checkRoleIsStore(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE) || role.equals(UserTable.Role.ADMIN);
        if (checkRole) {
            throw StoreException.roleUserNotAllowed();
        }
    }
}
