package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.model.MapObject;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StoreBisiness {

    public final TokenService tokenService;
    public final StoreService storeService;
    public final UserService userService;
    public final FileService fileService;

    public StoreBisiness(TokenService tokenService, StoreService storeService, UserService userService, FileService fileService) {
        this.tokenService = tokenService;
        this.storeService = storeService;
        this.userService = userService;
        this.fileService = fileService;
    }


    public List<StoreTable> findAllStore() {
        List<StoreTable> store = storeService.findAllStore();
        return store;
    }


    @SneakyThrows
    public Object getMyProfileStore(){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if ((role.equals(UserTable.Role.ADMIN)) || (role.equals(UserTable.Role.AFFILIATE))
                || (role.equals(UserTable.Role.USER))){
            throw  StoreException.roleUserNotAllowed();
        }
        StoreTable store = storeService.findByUserId(user);
        return store;
    }



    @SneakyThrows
    public Object register(StoreRegisterRequest request){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN)||role.equals(UserTable.Role.ST_AF)) {
            throw StoreException.storeRequestInvalid();
        }
        request.valid();
        storeService.register(user.getUserId(), request.getStore(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber());
        if (role.equals(UserTable.Role.USER)) {
            role = UserTable.Role.STORE;
        } else {
            role = UserTable.Role.ST_AF;
        }
        userService.updateRole(user, role);
        return new Response().success("register success");
    }


    public  Object updateStore(StoreRegisterRequest request) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        request.valid();
        storeService.updateStore(store, request.getStore(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber());
        return new Response().success("update store success");
    }


    public Object updateStatusStore(){
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        storeService.updateStatusStore(store);
        return new Response().success("delete store success");
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
