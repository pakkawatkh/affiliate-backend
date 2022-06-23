package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreBisiness {

    public final TokenService tokenService;
    public final StoreService storeService;
    public final UserService userService;

    public StoreBisiness(TokenService tokenService, StoreService storeService, UserService userService) {
        this.tokenService = tokenService;
        this.storeService = storeService;
        this.userService = userService;
    }


    public List<StoreTable> findAllStore() {
        List<StoreTable> store = storeService.findAllStore();
        return store;
    }

    public Object register(StoreRegisterRequest request) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        request.valid();
        System.out.println(request);
        storeService.register(user.getUserId(), request.getStore(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber());
        userService.updateRole(user, UserTable.Role.STORE);
        return new Response().success("register success");
    }



}
