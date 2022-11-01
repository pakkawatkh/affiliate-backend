package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AdminBusiness;
import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminBusiness adminBusiness;

    private final UserBusiness userBusiness;

    public AdminController(AdminBusiness adminBusiness, UserBusiness userBusiness) {
        this.adminBusiness = adminBusiness;
        this.userBusiness = userBusiness;
    }


    //   GET
    @GetMapping("/get-all-user")
    public List<UserTable> getAllUser(){
        List<UserTable> user = adminBusiness.findAllUser();
        return user;
    }


    @GetMapping("/get-all-store")
    public List<UserTable> getAllStore(){
        List<UserTable> user = adminBusiness.findAllStore();
        return user;
    }


    @GetMapping("/get-all-affiliate")
    public List<UserTable> getAllAffiliate(){
        List<UserTable> user = adminBusiness.findAllAffiliate();
        return user;
    }


}
