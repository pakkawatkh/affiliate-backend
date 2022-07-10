package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.model.MapObject;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
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


    public Object getMyProfileStore() throws BaseException{
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if ((role.equals(UserTable.Role.ADMIN)) || (role.equals(UserTable.Role.AFFILIATE))
                || (role.equals(UserTable.Role.USER))){
            throw  StoreException.roleUserNotAllowed();
        }
        StoreTable store = storeService.findByUserId(user);
        return store;
    }

    public Object register(MultipartFile file, Object profile) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN)||role.equals(UserTable.Role.ST_AF)) {
            throw StoreException.storeRequestInvalid();
        }
        MapObject object = new MapObject();
        StoreRegisterRequest request = object.toRegisterStore(profile);
        request.valid();
        System.out.println(request);
        String img = fileService.saveImg(file, "/uploads/profile");
        storeService.register(user.getUserId(), request.getStore(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber(), img);
        if (role.equals(UserTable.Role.USER)) {
            role = UserTable.Role.STORE;
        } else {
            role = UserTable.Role.ST_AF;
        }
        userService.updateRole(user, role);
        return new Response().success("register success");
    }


    public  Object updateStore(MultipartFile file, Object profile) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE)) {
            throw StoreException.roleUserNotAllowed();
        }
        StoreTable store = storeService.findByUserId2(user);
        MapObject object = new MapObject();
        StoreRegisterRequest request = object.toRegisterStore(profile);
        request.valid();
        String img = fileService.saveImg(file, "/uploads/profile");
        storeService.updateStore(store, request.getStore(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber(), img);
        return new Response().success("update store success");
    }


    public Object updateStatusStore() throws BaseException{
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE)) {
            throw StoreException.roleUserNotAllowed();
        }
        StoreTable store = storeService.findByUserId2(user);
        storeService.updateStatusStore(store);
        return new Response().success("delete store success");
    }



}
