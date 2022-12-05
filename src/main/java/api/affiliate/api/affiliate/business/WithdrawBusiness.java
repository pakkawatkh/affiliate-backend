package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.entity.WithdrawTable;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.exception.WithdrawException;
import api.affiliate.api.affiliate.mapper.WithdrawMapper;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.store.StoreRequest;
import api.affiliate.api.affiliate.model.withdraw.WithdrawResponse;
import api.affiliate.api.affiliate.service.OrderListService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.WithdrawService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WithdrawBusiness {

    public final OrderListService orderService;
    private final WithdrawService withdrawService;
    private final TokenService tokenService;
    private final StoreService storeService;
    private final WithdrawMapper withdrawMapper;
    private final UserService userService;


    public Object findByWithdrawById(Integer withdrawId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAdmin(user);
        WithdrawTable withdraw = withdrawService.findById(withdrawId);
        WithdrawResponse responses = withdrawMapper.toWithdrawResponse(withdraw);
        StoreTable storeTable = storeService.findByStoreId(withdraw.getStoreId());
        StoreRequest store = withdrawMapper.toStoreRequest(storeTable);
        responses.setStore(store);
        UserTable userTable = userService.findById(storeTable.getUserId());
        responses.setUser(userTable);
        return responses;
    }

    @SneakyThrows
    public Object createWithdrawByOrder() {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId(user.getUserId());
        int i = orderService.getTotalPriceByOrderStatusSuccess(store.getStoreId());
        if (i == 0) {
            throw WithdrawException.withdrawFail();
        }
        WithdrawTable withdraw = withdrawService.createWithdrawByOrder(i, store.getStoreId());
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
