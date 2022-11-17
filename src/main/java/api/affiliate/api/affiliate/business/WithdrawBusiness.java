package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.exception.WithdrawException;
import api.affiliate.api.affiliate.model.Response;
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


    @SneakyThrows
    public Object createWithdrawByOrder() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId(user.getUserId());
        int i = orderService.getTotalPriceByOrderStatusSuccess(store.getStoreId());
//        int i = checkstatusSuccess == null?0 : Integer.parseInt((String) checkstatusSuccess);
        if (i == 0){
            throw OrderException.orderNull();
//            คุณไม่มียอดเงินสำหรับถอน
        }
        WithdrawTable withdraw = withdrawService.createWithdrawByOrder(i);
        orderService.updateOrderStatusIsWithDrawMoney(withdraw.getWithdrawId(), store.getStoreId());
        return new Response().success("update order status withdraw money");
    }


    @SneakyThrows
    public void checkRoleIsStore(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE) || role.equals(UserTable.Role.ADMIN);
        if (checkRole) {
            throw StoreException.roleUserNotAllowed();
        }
    }
}
