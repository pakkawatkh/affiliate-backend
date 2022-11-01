package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.admin.AdminRegisterRequest;
import api.affiliate.api.affiliate.service.AdminService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBusiness {



    private final TokenService tokenService;
    private final AdminService adminService;
    private final UserService userService;


    public AdminBusiness(TokenService tokenService, AdminService adminService, UserService userService) {
        this.tokenService = tokenService;
        this.adminService = adminService;
        this.userService = userService;
    }


    public Object register(AdminRegisterRequest request) throws BaseException {
        System.out.println(request);
        return new Response().success("register success");
    }



//    public List<UserTable> findAllUser(){
//        UserTable user = tokenService.getUserByToken();
//        System.out.println("USER"+user);
//        checkRoleIsAdmin(user);
//        List<UserTable> user1 = adminService.getAllUser();
//        return user1;
//    }


    public List<UserTable> findAllUser(){
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER"+user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.USER);
        return user1;
    }


    public List<UserTable> findAllStore(){
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER " +user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.ST_AF, UserTable.Role.STORE);
        System.out.println(user1);
        return user1;
    }


    public List<UserTable> findAllAffiliate(){
        UserTable user = tokenService.getUserByToken();
        System.out.println("USER " +user);
        checkRoleIsAdmin(user);
        List<UserTable> user1 = adminService.getAllRole(UserTable.Role.AFFILIATE, UserTable.Role.ST_AF);
        System.out.println(user1);
        return user1;
    }


    @SneakyThrows
    public void checkRoleIsAdmin(UserTable user){
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.STORE)
                || role.equals(UserTable.Role.AFFILIATE)
                || role.equals(UserTable.Role.USER)
                || role.equals(UserTable.Role.ST_AF);
        if (checkRole){
            throw UserException.roleUserNotAllowed();
        }
    }


}